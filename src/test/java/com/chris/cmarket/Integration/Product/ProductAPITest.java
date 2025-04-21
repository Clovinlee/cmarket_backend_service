package com.chris.cmarket.Integration.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.chris.cmarket.Dtos.ProductDTO;
import com.chris.cmarket.Requests.GetProductRequest;
import com.chris.cmarket.Responses.APIResponse;
import com.chris.cmarket.Utils.CustomPageImpl;
import com.chris.cmarket.Utils.LoggerGetter;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductAPITest {

    @Autowired
    private TestRestTemplate restTemplate;

    public static Logger logger = LoggerGetter.getLogger(ProductAPITest.class);

    @Test
    void shouldGetProductsPagination() {
        ResponseEntity<APIResponse<CustomPageImpl<ProductDTO>>> response = restTemplate.exchange(
                "/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<APIResponse<CustomPageImpl<ProductDTO>>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());

        CustomPageImpl<ProductDTO> data = response.getBody().getData();
        assertEquals(10, data.getTotalElements());
    }

    @Test
    @MethodSource("getProductPaginationWithFilterProvider")
    void shouldGetProductsPaginationWithFilters(GetProductRequest getRequest) {
        ResponseEntity<APIResponse<CustomPageImpl<ProductDTO>>> response = restTemplate.exchange(
                "/products" + getRequest.toQueryParam(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<APIResponse<CustomPageImpl<ProductDTO>>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<ProductDTO> responseProducts = response.getBody().getData().getContent();
        for (ProductDTO product : responseProducts) {
            if (getRequest.getName() != null) {
                assertTrue(product.getName().toLowerCase().contains(getRequest.getName().toLowerCase()));
            }
            if (getRequest.getMinPrice() != null) {
                assertTrue(product.getPrice().compareTo(getRequest.getMinPrice()) >= 0);
            }
            if (getRequest.getMaxPrice() != null) {
                assertTrue(product.getPrice().compareTo(getRequest.getMaxPrice()) <= 0);
            }
            if (getRequest.getRarity() != null) {
                assertEquals(getRequest.getRarity(), product.getIdRarity());
            }
            if (getRequest.getMerchant() != null) {
                // assertEquals(getRequest.getMerchant(), product.getMerchantId());
            }
        }
    }

    /**
     * Provides test parameters for the method
     * {@code shouldGetProductsPaginationWithFilters}.
     *
     * @return a list of {@link GetProductRequest} with filter parameters
     */
    private static List<GetProductRequest> getProductPaginationWithFilterProvider() {
        return List.of(
                GetProductRequest.builder().name("ring").build(),
                GetProductRequest.builder().name("ring").minPrice(new BigDecimal(100)).build(),
                GetProductRequest.builder().name("ring").maxPrice(new BigDecimal(100)).build(),
                GetProductRequest.builder().rarity(4L).build(),
                GetProductRequest.builder().merchant(2L).build());
    }
}

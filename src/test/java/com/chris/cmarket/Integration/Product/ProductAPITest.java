package com.chris.cmarket.Integration.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.chris.cmarket.Dtos.MerchantDTO;
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

    @ParameterizedTest
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
            if (null != getRequest.getName()) {
                assertTrue(product.getName().toLowerCase().contains(getRequest.getName().toLowerCase()));
            }
            if (null != getRequest.getMinPrice()) {
                assertTrue(product.getPrice().compareTo(getRequest.getMinPrice()) >= 0);
            }
            if (null != getRequest.getMaxPrice()) {
                assertTrue(product.getPrice().compareTo(getRequest.getMaxPrice()) <= 0);
            }
            if (null != getRequest.getRarity()) {
                assertEquals(getRequest.getRarity(), product.getIdRarity());
            }
            if (null != getRequest.getMerchants()) {
                List<Long> productMerchantIds = product.getMerchants()
                        .stream()
                        .map(MerchantDTO::getLevel)
                        .collect(Collectors.toList());

                boolean hasAtLeastOne = getRequest.getMerchants()
                        .stream()
                        .anyMatch(productMerchantIds::contains);

                assertTrue(hasAtLeastOne);
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
                GetProductRequest.builder().merchants(Arrays.asList(2L, 3L)).build());
    }
}

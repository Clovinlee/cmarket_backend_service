package com.chris.cmarket.Integration.Product;

import com.chris.cmarket.Common.Dto.CustomPageImplDto;
import com.chris.cmarket.Common.Response.APIResponse;
import com.chris.cmarket.Merchant.Dto.MerchantDTO;
import com.chris.cmarket.Product.Dto.ProductDTO;
import com.chris.cmarket.Product.Model.ProductModel;
import com.chris.cmarket.Product.Repository.ProductRepository;
import com.chris.cmarket.Product.Request.GetProductRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ProductAPITest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final TypeReference<APIResponse<CustomPageImplDto<ProductDTO>>> productAPIResponseTypeReference =
            new TypeReference<>() {
            };

    @Autowired
    private MockMvc mockMvc;

    /**
     * {@link com.chris.cmarket.Infrastructure.Config.CacheConfig} will use simpleCache for test
     */
    @Autowired
    private CacheManager cacheManager;

    @MockitoSpyBean
    private ProductRepository productRepository;

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

    @BeforeEach
    void clearCaches() {
        cacheManager.getCacheNames().forEach(name -> {
            cacheManager.getCache(name).clear();
        });
    }

    @Test
    void shouldGetCachedProducts() throws Exception {
        mockMvc.perform(get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Cache cache = cacheManager.getCache("product");
        assertNotNull(cache);

        mockMvc.perform(get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(productRepository, times(1))
                .findAll(
                        ArgumentMatchers.<Specification<ProductModel>>any(),
                        any(Pageable.class)
                );
    }

    @Test
    void shouldGetProductsPagination() throws Exception {
        MvcResult result = mockMvc.perform(get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        APIResponse<CustomPageImplDto<ProductDTO>> apiResponse = objectMapper.readValue(
                responseJson,
                productAPIResponseTypeReference
        );

        CustomPageImplDto<ProductDTO> data = apiResponse.getData();

        assertEquals(10, data.getTotalElements());
    }

    @ParameterizedTest
    @MethodSource("getProductPaginationWithFilterProvider")
    void shouldGetProductsPaginationWithFilters(GetProductRequest getRequest) throws Exception {
        MvcResult result = mockMvc.perform(
                        get("/products" + getRequest.toQueryParam())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        APIResponse<CustomPageImplDto<ProductDTO>> apiResponse = objectMapper.readValue(
                responseJson,
                new TypeReference<APIResponse<CustomPageImplDto<ProductDTO>>>() {
                }
        );

        List<ProductDTO> responseProducts = apiResponse.getData().getContent();

        // Assertions per filter
        for (ProductDTO product : responseProducts) {
            if (null != getRequest.getName()) {
                assertTrue(product.getName().toLowerCase().contains(getRequest.getName().toLowerCase()));
            }
            if (null != getRequest.getMinPrice()) {
                assertTrue(product.getPrice().compareTo(getRequest.getMinPrice()) >= 0); // price should be greater equal than minPrice
            }
            if (null != getRequest.getMaxPrice()) {
                assertTrue(product.getPrice().compareTo(getRequest.getMaxPrice()) <= 0); // price should be lesser equal than maxPrice
            }
            if (null != getRequest.getRarity()) {
                assertEquals(getRequest.getRarity(), product.getIdRarity());
            }
            if (null != getRequest.getMerchants()) {
                List<Long> productMerchantIds = product.getMerchants()
                        .stream()
                        .map(MerchantDTO::getLevel)
                        .toList();

                boolean hasAtLeastOne = getRequest.getMerchants()
                        .stream()
                        .anyMatch(productMerchantIds::contains);

                assertTrue(hasAtLeastOne);
            }
        }
    }
}

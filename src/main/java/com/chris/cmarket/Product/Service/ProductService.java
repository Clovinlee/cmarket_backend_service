package com.chris.cmarket.Product.Service;

import com.chris.cmarket.Common.Dto.CustomPageImplDto;
import com.chris.cmarket.Common.Exception.NotFoundException;
import com.chris.cmarket.Common.Response.APIResponse;
import com.chris.cmarket.Common.Specification.NameSpecification;
import com.chris.cmarket.Common.Specification.PriceSpecification;
import com.chris.cmarket.Common.Specification.SpecificationBuilder;
import com.chris.cmarket.Common.Specification.WithRelationSpecification;
import com.chris.cmarket.Order.Client.OrderClient;
import com.chris.cmarket.Order.Event.PlaceOrderEvent;
import com.chris.cmarket.Product.Dto.ProductDTO;
import com.chris.cmarket.Product.Exception.StockNotEnoughException;
import com.chris.cmarket.Product.Model.ProductModel;
import com.chris.cmarket.Product.Repository.ProductRepository;
import com.chris.cmarket.Product.Request.GetProductRequest;
import com.chris.cmarket.Product.Specification.ProductSpecification;
import com.chris.cmarket.Rarity.Specification.RarityIdSpecification;
import com.chris.cmarket.User.Model.UserModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private ProductRepository productRepository;
    private KafkaTemplate<String, PlaceOrderEvent> kafkaTemplate;
    private OrderClient orderClient;

    /**
     * Retrieves a product by its slug.
     *
     * @param slug the unique identifier for the product.
     * @return an {@link Optional} containing the {@link ProductDTO} if found,
     * otherwise empty.
     */
    public Optional<ProductDTO> getProductBySlug(String slug) {
        Optional<ProductModel> optProductModel = this.getProductModelBySlug(slug);

        return optProductModel.map(ProductDTO::new);
    }

    /**
     * Retrieves a product by its slug.
     *
     * @param slug the unique identifier for the product.
     * @return an {@link Optional} containing the {@link ProductModel} if found,
     * otherwise empty.
     */
    public Optional<ProductModel> getProductModelBySlug(String slug) {
        return productRepository.findBySlug(slug);
    }

    /**
     * Retrieves a page of products based on the provided search parameters.
     *
     * @param param the request parameters containing the filter criteria.
     * @return a {@link Page} of {@link ProductDTO} containing the filtered
     * products.
     */
    @Cacheable(value = "product", key = "#param.toQueryParam()")
    public CustomPageImplDto<ProductDTO> getProducts(GetProductRequest param) {
        int page = param.getZeroBasedPage();
        int size = param.getSize();

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

        SpecificationBuilder<ProductModel> builder = new SpecificationBuilder<>();

        builder.addCriteria(PriceSpecification.withMax(param.getMaxPrice()))
                .addCriteria(PriceSpecification.withMin(param.getMinPrice()))
                .addCriteria(NameSpecification.nameLike(param.getName()))
                .addCriteria(RarityIdSpecification.whereId(param.getRarity()))
                .addCriteria(ProductSpecification.hasMerchant(param.getMerchants()))
                .addCriteria(WithRelationSpecification.fetch("rarity", "productMerchants"));

        Page<ProductModel> resultPage = productRepository.findAll(builder.build(), pageable);
        Page<ProductDTO> dtoPage = resultPage.map(ProductDTO::new);

        return new CustomPageImplDto<>(dtoPage);
    }

    @Transactional
    public void placeOrder(String slug, int quantity, UserModel user, String jwtToken) {
        String orderUuid;

        try {
            APIResponse<String> orderUuidResponse = orderClient.getOrderUuid(jwtToken);
            orderUuid = orderUuidResponse.getData();
        } catch (Throwable th) {
            log.error("Error fetching order UUID: {}", th.getMessage(), th);

            throw th;
        }

        ProductModel product = this.productRepository.findBySlugForUpdate(slug).orElseThrow(
                () -> new NotFoundException("Product " + slug + " not found"));
        this.deduceStock(product, quantity);

        PlaceOrderEvent placeOrderEvent = new PlaceOrderEvent(slug, quantity, product.getId(), product.getPrice(), user.getId(), orderUuid);

        kafkaTemplate.send(PlaceOrderEvent.TOPIC_NAME, placeOrderEvent);
        log.info("Order event sent to kafka: {}", placeOrderEvent);
    }

    @Transactional
    public void deduceStock(ProductModel product, int quantity) {
        if (product.getQuantity() - quantity < 0) throw new StockNotEnoughException(product.getSlug());

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }
}

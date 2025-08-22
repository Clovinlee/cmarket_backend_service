package com.chris.cmarket.Product.Service;

import com.chris.cmarket.Common.Dto.CustomPageImplDto;
import com.chris.cmarket.Common.Specification.NameSpecification;
import com.chris.cmarket.Common.Specification.PriceSpecification;
import com.chris.cmarket.Common.Specification.SpecificationBuilder;
import com.chris.cmarket.Common.Specification.WithRelationSpecification;
import com.chris.cmarket.Product.Dto.ProductDTO;
import com.chris.cmarket.Product.Model.ProductModel;
import com.chris.cmarket.Product.Repository.ProductRepository;
import com.chris.cmarket.Product.Request.GetProductRequest;
import com.chris.cmarket.Product.Specification.ProductSpecification;
import com.chris.cmarket.Rarity.Specification.RarityIdSpecification;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    /**
     * Retrieves a product by its slug.
     *
     * @param slug the unique identifier for the product.
     * @return an {@link Optional} containing the {@link ProductDTO} if found,
     *         otherwise empty.
     */
    public Optional<ProductDTO> getProductBySlug(String slug) {
        Optional<ProductModel> optProductModel = productRepository.findBySlug(slug);

        return optProductModel.map(ProductDTO::new);
    }

    /**
     * Retrieves a page of products based on the provided search parameters.
     *
     * @param param the request parameters containing the filter criteria.
     * @return a {@link Page} of {@link ProductDTO} containing the filtered
     *         products.
     */
    @Cacheable(value = "product", key = "#param.toQueryParam()")
    public CustomPageImplDto<ProductDTO> getProducts(GetProductRequest param) {
        System.out.println("CHRIS FROM SERVICE HERE!");
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

}

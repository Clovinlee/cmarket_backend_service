package com.chris.cmarket.Product.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.chris.cmarket.Common.Specification.NameSpecification;
import com.chris.cmarket.Common.Specification.PriceSpecification;
import com.chris.cmarket.Common.Specification.WithRelationSpecification;
import com.chris.cmarket.Product.Dto.ProductDTO;
import com.chris.cmarket.Product.Model.ProductModel;
import com.chris.cmarket.Product.Repository.ProductRepository;
import com.chris.cmarket.Product.Request.GetProductRequest;
import com.chris.cmarket.Product.Specification.ProductSpecification;
import com.chris.cmarket.Rarity.Specification.RarityIdSpecification;
import com.chris.cmarket.Utils.SpecificationBuilder;

import lombok.AllArgsConstructor;

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

        return optProductModel.map(productModel -> new ProductDTO(productModel));
    }

    /**
     * Retrieves a page of products based on the provided search parameters.
     *
     * @param param the request parameters containing the filter criteria.
     * @return a {@link Page} of {@link ProductDTO} containing the filtered
     *         products.
     */
    public Page<ProductDTO> getProducts(GetProductRequest param) {
        int page = param.getZeroBasedPage();
        int size = param.getSize();

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

        SpecificationBuilder<ProductModel> builder = new SpecificationBuilder<ProductModel>();

        builder.addCriteria(PriceSpecification.withMax(param.getMaxPrice()))
                .addCriteria(PriceSpecification.withMin(param.getMinPrice()))
                .addCriteria(NameSpecification.nameLike(param.getName()))
                .addCriteria(RarityIdSpecification.whereId(param.getRarity()))
                .addCriteria(ProductSpecification.hasMerchant(param.getMerchants()))
                .addCriteria(WithRelationSpecification.fetch("rarity", "productMerchants"));

        Page<ProductModel> resultPage = productRepository.findAll(builder.build(), pageable);

        List<ProductDTO> dtos = resultPage.stream()
                .map(product -> new ProductDTO(product))
                .toList();

        return new PageImpl<>(dtos, pageable, resultPage.getTotalElements());
    }

}

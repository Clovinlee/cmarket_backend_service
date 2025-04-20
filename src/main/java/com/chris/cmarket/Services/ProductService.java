package com.chris.cmarket.Services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.chris.cmarket.Dtos.ProductDTO;
import com.chris.cmarket.Models.ProductModel;
import com.chris.cmarket.Repositories.ProductRepository;
import com.chris.cmarket.Requests.GetProductRequest;
import com.chris.cmarket.Specifications.Globals.NameSpecification;
import com.chris.cmarket.Specifications.Globals.PriceSpecification;
import com.chris.cmarket.Specifications.Globals.WithRelationSpecification;
import com.chris.cmarket.Specifications.Rarities.RarityIdSpecification;
import com.chris.cmarket.Utils.SpecificationBuilder;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public Page<ProductDTO> getProducts(GetProductRequest param) {
        int page = param.getZeroBasedPage();
        int size = param.getSize();

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

        SpecificationBuilder<ProductModel> builder = new SpecificationBuilder<ProductModel>();

        builder.addCriteria(PriceSpecification.withMax(param.getMaxPrice()))
                .addCriteria(PriceSpecification.withMin(param.getMinPrice()))
                .addCriteria(NameSpecification.nameLike(param.getName()))
                .addCriteria(RarityIdSpecification.whereId(param.getRarity()))
                .addCriteria(WithRelationSpecification.fetch("rarity"))
                .addCriteria(WithRelationSpecification.fetch("productMerchants"));

        Page<ProductModel> resultPage = productRepository.findAll(builder.build(), pageable);

        List<ProductDTO> dtos = resultPage.stream()
                .map(product -> new ProductDTO(product))
                .toList();

        return new PageImpl<>(dtos, pageable, resultPage.getTotalElements());
    }

}

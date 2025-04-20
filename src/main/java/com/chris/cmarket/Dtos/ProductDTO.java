package com.chris.cmarket.Dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;

import com.chris.cmarket.Models.ProductModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(Include.NON_ABSENT)
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Optional<RarityDTO> rarity;
    private List<MerchantDTO> merchants;

    @JsonProperty("id_rarity")
    private Long idRarity;

    public ProductDTO(ProductModel product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.rarity = Optional.empty();
        this.idRarity = product.getIdRarity();

        if (Hibernate.isInitialized(product.getRarity())) {
            this.rarity = Optional.of(new RarityDTO(product.getRarity()));
        }

        if (Hibernate.isInitialized(product.getProductMerchants())) {
            this.merchants = product.getProductMerchants().stream()
                    .map(pm -> new MerchantDTO(pm.getMerchant()))
                    .collect(Collectors.toList());
        }
    }
}
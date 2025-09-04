package com.chris.cmarket.Product.Dto;

import com.chris.cmarket.Merchant.Dto.MerchantDTO;
import com.chris.cmarket.Product.Model.ProductModel;
import com.chris.cmarket.Rarity.Dto.RarityDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@NoArgsConstructor
@JsonInclude(Include.NON_ABSENT)
public class ProductDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String image;
    private String description;
    private BigDecimal price;
    private RarityDTO rarity;
    private List<MerchantDTO> merchants;

    @JsonProperty("rarity_id")
    private Long rarityId;

    public ProductDTO(ProductModel product) {
        this.id = product.getId();
        this.name = product.getName();
        this.image = product.getImage();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.rarity = null;
        this.rarityId = product.getRarityId();

        if (Hibernate.isInitialized(product.getRarity())) {
            this.rarity = new RarityDTO(product.getRarity());
        }

        if (Hibernate.isInitialized(product.getProductMerchants())) {
            this.merchants = product.getProductMerchants().stream()
                    .map(pm -> new MerchantDTO(pm.getMerchant()))
                    .collect(Collectors.toList());
        }
    }
}
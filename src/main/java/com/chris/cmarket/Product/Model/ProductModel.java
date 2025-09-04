package com.chris.cmarket.Product.Model;

import java.math.BigDecimal;
import java.util.List;

import com.chris.cmarket.ProductMerchant.Model.ProductMerchantModel;
import com.chris.cmarket.Rarity.Model.RarityModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String slug;
    private String description;

    @Column(name = "rarity_id", updatable = false, insertable = false)
    private Long rarityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rarity_id")
    private RarityModel rarity;

    @Positive
    private BigDecimal price;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductMerchantModel> productMerchants;
}
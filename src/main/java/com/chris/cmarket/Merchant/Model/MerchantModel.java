package com.chris.cmarket.Merchant.Model;

import java.time.LocalDateTime;
import java.util.List;

import com.chris.cmarket.ProductMerchant.Model.ProductMerchantModel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "merchants")
@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Needed by JPA
@AllArgsConstructor
public class MerchantModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;
    private Long level;
    private String color;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY)
    private List<ProductMerchantModel> productMerchants;
}
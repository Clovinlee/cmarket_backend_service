package com.chris.cmarket.Models;

import java.util.List;
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
    private Integer id;

    private String name;
    private Integer level;
    private String color;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY)
    private List<ProductMerchantModel> productMerchants;
}
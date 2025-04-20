package com.chris.cmarket.Dtos;

import com.chris.cmarket.Models.MerchantModel;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MerchantDTO {
    private String name;
    private Integer level;
    private String color;

    public MerchantDTO(MerchantModel merchant) {
        this.name = merchant.getName();
        this.level = merchant.getLevel();
        this.color = merchant.getColor();
    }
}

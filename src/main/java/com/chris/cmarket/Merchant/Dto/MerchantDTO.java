package com.chris.cmarket.Merchant.Dto;

import com.chris.cmarket.Merchant.Model.MerchantModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@ToString
@NoArgsConstructor
public class MerchantDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private Long level;
    private String color;

    public MerchantDTO(MerchantModel merchant) {
        this.name = merchant.getName();
        this.level = merchant.getLevel();
        this.color = merchant.getColor();
    }
}

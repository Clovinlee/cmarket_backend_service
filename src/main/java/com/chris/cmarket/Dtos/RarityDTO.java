package com.chris.cmarket.Dtos;

import com.chris.cmarket.Models.RarityModel;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RarityDTO {
    private Long id;
    private String name;
    private Integer level;
    private String color;

    public RarityDTO(RarityModel rarity) {
        this.id = rarity.getId();
        this.name = rarity.getName();
        this.level = rarity.getLevel();
        this.color = rarity.getColor();
    }
}
package com.chris.cmarket.Rarity.Dto;

import com.chris.cmarket.Rarity.Model.RarityModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@ToString
@NoArgsConstructor
public class RarityDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

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
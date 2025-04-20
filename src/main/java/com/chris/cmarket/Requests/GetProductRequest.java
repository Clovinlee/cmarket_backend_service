package com.chris.cmarket.Requests;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class GetProductRequest {
    private Double minPrice;
    private Double maxPrice;
    private String name;

    @Positive
    @Nullable
    private Long rarity;
    private Integer merchant;
    private Integer page = 1;
    private Integer size = 10;

    public Integer getZeroBasedPage() {
        return this.page - 1;
    }
}

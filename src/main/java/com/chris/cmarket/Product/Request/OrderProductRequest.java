package com.chris.cmarket.Product.Request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class OrderProductRequest {
    @Min(1)
    private Integer quantity;
}

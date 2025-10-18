package com.chris.cmarket.Order.Event;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderEvent {

    /**
     * Kafka Topic name
     */
    public static final String TOPIC_NAME = "place_order";

    private String slug;

    private Integer quantity;

    @JsonProperty("product_id")
    private Long productId;

    @Positive
    @JsonProperty("price_item")
    private BigDecimal priceItem;

    @JsonProperty("user_id")
    private Long userId;
}

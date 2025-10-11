package com.chris.cmarket.Order.Event;

import com.chris.cmarket.Order.Exception.PlaceOrderException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RevertPlaceOrderEvent {
    /**
     * Kafka Topic name
     */
    public static final String TOPIC_NAME = "revert_place_order";

    public PlaceOrderEvent placeOrderEvent;

    public PlaceOrderException exception;
}

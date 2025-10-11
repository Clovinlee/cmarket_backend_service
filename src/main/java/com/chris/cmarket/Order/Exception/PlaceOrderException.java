package com.chris.cmarket.Order.Exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;

public class PlaceOrderException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("error_message")
    private final String errorMessage;

    @JsonProperty("error_type")
    private final String errorType;

    public PlaceOrderException(String errorMessage, String errorType, Throwable throwable) {
        super(errorMessage, throwable);
        this.errorMessage = errorMessage;
        this.errorType = errorType;
    }
}
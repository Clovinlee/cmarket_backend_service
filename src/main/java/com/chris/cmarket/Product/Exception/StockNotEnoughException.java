package com.chris.cmarket.Product.Exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class StockNotEnoughException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String messageKey;

    public StockNotEnoughException(String messageKey, Throwable err) {
        super(messageKey == null ? "error.product.empty_stock" : messageKey, err);
        this.messageKey = messageKey == null ? "error.product.empty_stock" : messageKey;
    }

    public StockNotEnoughException(String messageKey) {
        this(messageKey, null);
    }

    public StockNotEnoughException() {
        this("The product does not have enough stock, please try again later", null);
    }
}

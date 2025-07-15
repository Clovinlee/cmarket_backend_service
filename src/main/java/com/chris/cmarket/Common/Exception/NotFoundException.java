package com.chris.cmarket.Common.Exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String messageKey;

    public NotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        this.messageKey = "error.notfound"; // Default message key
    }

    public NotFoundException(String errorMessage) {
        this(errorMessage, null);
    }

    public NotFoundException() {
        this("The requested resource was not found.", null);
    }
}

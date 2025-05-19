package com.chris.cmarket.Exceptions;

public class NotFoundException extends RuntimeException {

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

    public String getMessageKey() {
        return messageKey;
    }
}

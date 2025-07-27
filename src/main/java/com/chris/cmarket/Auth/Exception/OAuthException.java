package com.chris.cmarket.Auth.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.io.Serial;

@AllArgsConstructor
@Getter
public class OAuthException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final HttpStatusCode statusCode;
    private final String uri;
    private final String response;

    public OAuthException(String message, HttpStatusCode statusCode, String uri, String response) {
        super(message);
        this.statusCode = statusCode;
        this.uri = uri;
        this.response = response;
    }

    public OAuthException(String message, Throwable cause, HttpStatusCode statusCode, String uri, String response) {
        super(message, cause);
        this.statusCode = statusCode;
        this.uri = uri;
        this.response = response;
    }
}

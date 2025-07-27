package com.chris.cmarket.Auth.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;

@Getter
@AllArgsConstructor
public class OAuthUserAlreadyExistException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;

    private String provider;

    private String providerId;
}
package com.chris.cmarket.Auth.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;

@Getter
@AllArgsConstructor
public class EmailAlreadyExistException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String email;
}
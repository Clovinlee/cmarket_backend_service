package com.chris.cmarket.Handlers.Auth;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.chris.cmarket.Constants.CmarketLoadOrderConstant;
import com.chris.cmarket.Exceptions.Auth.EmailAlreadyExistException;
import com.chris.cmarket.Responses.APIResponse;

@RestControllerAdvice()
@Order(CmarketLoadOrderConstant.DEFAULT_PRIORITY)
public class AuthHandler {

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<APIResponse<String>> handleEmailAlreadyExistException(EmailAlreadyExistException ex) {
        APIResponse<String> response = APIResponse.failed(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<APIResponse<String>> handleBadCredentialsException(BadCredentialsException ex) {
        APIResponse<String> response = APIResponse.failed(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

}

package com.chris.cmarket.Auth.Handler;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.chris.cmarket.Auth.Exception.EmailAlreadyExistException;
import com.chris.cmarket.Common.Constant.CmarketLoadOrderConstant;
import com.chris.cmarket.Common.Response.APIResponse;

@RestControllerAdvice
@Order(CmarketLoadOrderConstant.DEFAULT_PRIORITY)
public class AuthHandler {

    private MessageSource messageSource;

    public AuthHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<APIResponse<String>> handleEmailAlreadyExistException(EmailAlreadyExistException ex) {

        APIResponse<String> response = APIResponse.<String>builder()
                .message(this.messageSource.getMessage(
                        "error.email.exist",
                        new Object[] { ex.getEmail() },
                        LocaleContextHolder.getLocale()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<APIResponse<String>> handleBadCredentialsException(BadCredentialsException ex) {
        APIResponse<String> response = APIResponse.failed(
                this.messageSource.getMessage(
                        "error.auth.badcredentials",
                        null,
                        LocaleContextHolder.getLocale()));

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

}

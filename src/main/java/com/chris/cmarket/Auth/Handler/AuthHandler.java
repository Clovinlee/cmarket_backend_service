package com.chris.cmarket.Auth.Handler;

import com.chris.cmarket.Auth.Exception.EmailAlreadyExistException;
import com.chris.cmarket.Auth.Exception.OAuthException;
import com.chris.cmarket.Common.Constant.CmarketLoadOrderConstant;
import com.chris.cmarket.Common.Response.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(CmarketLoadOrderConstant.DEFAULT_PRIORITY)
@Slf4j
public class AuthHandler {
    private final MessageSource messageSource;

    private final boolean isDebugMode;

    public AuthHandler(MessageSource messageSource, @Value("${app.debug-mode:false}") boolean isDebugMode) {
        this.messageSource = messageSource;
        this.isDebugMode = isDebugMode;
    }

    @ExceptionHandler(OAuthException.class)
    public ResponseEntity<APIResponse<String>> handleOAuthException(OAuthException ex) {
        if (isDebugMode) {
            log.error("[OAuthException-Debug] URI: {}, Status: {}, Response: {}, Message: {}",
                    ex.getUri(),
                    ex.getStatusCode(),
                    ex.getResponse(),
                    ex.getMessage(),
                    ex);
        }

        APIResponse<String> response = APIResponse.failed("OAuth error: " + ex.getMessage());
        return new ResponseEntity<>(response, ex.getStatusCode());
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

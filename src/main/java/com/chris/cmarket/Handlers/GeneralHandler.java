package com.chris.cmarket.Handlers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.chris.cmarket.Exceptions.NotFoundException;
import com.chris.cmarket.Responses.APIResponse;

@RestControllerAdvice
@Order(Integer.MAX_VALUE)
public class GeneralHandler {

    @Value("${app.debug-mode:false}")
    private boolean isDebugMode;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<APIResponse<String>> handleNotFoundException(NotFoundException ex) {
        APIResponse<String> response = APIResponse.failed(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<String>> handleGeneralExceptions(Exception ex) throws Exception {
        if (isDebugMode) {
            ex.printStackTrace();

            throw ex;
        }
        APIResponse<String> response = APIResponse.failed("Internal server error");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

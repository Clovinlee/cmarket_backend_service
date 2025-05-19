package com.chris.cmarket.Handlers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.chris.cmarket.Constants.CmarketLoadOrderConstant;
import com.chris.cmarket.Responses.APIResponse;

@RestControllerAdvice
@Order(CmarketLoadOrderConstant.DEFAULT_PRIORITY)
public class ValidationHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Map<String, List<String>>>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, List<String>> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        error -> error.getField(),
                        Collectors.mapping(error -> error.getDefaultMessage(), Collectors.toList())));
        APIResponse<Map<String, List<String>>> response = APIResponse.failed(errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<APIResponse<String>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        APIResponse<String> response = APIResponse.failed("Request body is missing or malformed");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

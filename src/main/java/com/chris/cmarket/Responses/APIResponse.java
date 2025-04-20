package com.chris.cmarket.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class APIResponse<T> {
    private boolean success;

    private T data;

    @Builder.Default
    private String message = "";

    public static <T> APIResponse<T> success(T data) {
        return APIResponse.<T>builder()
                .success(true)
                .data(data)
                .build();
    }

    public static <T> APIResponse<T> failed() {
        return APIResponse.<T>builder()
                .success(false)
                .message("Something went wrong")
                .build();
    }

    public static <T> APIResponse<T> failed(T data) {
        return APIResponse.<T>builder()
                .success(false)
                .message("Something went wrong")
                .data(data)
                .build();
    }

    public static <T> APIResponse<T> notFound() {
        return APIResponse.<T>builder()
                .success(false)
                .message("Data not found")
                .build();
    }
}

package com.chris.cmarket.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Generic API response wrapper for standardizing success and error responses.
 *
 * @param <T> the type of the response data
 */
@Data
@AllArgsConstructor
@Builder
public class APIResponse<T> {

    /** Indicates whether the request was successful. */
    private boolean success;

    /** The response payload. */
    private T data;

    /** A message describing the result. */
    @Builder.Default
    private String message = "";

    /**
     * Creates a successful API response with data.
     *
     * @param data the response data
     * @return a successful API response
     */
    public static <T> APIResponse<T> success(T data) {
        return APIResponse.<T>builder()
                .success(true)
                .data(data)
                .build();
    }

    /**
     * Creates a failed API response with a default error message.
     *
     * @return a failed API response
     */
    public static <T> APIResponse<T> failed() {
        return APIResponse.<T>builder()
                .success(false)
                .message("Something went wrong")
                .build();
    }

    /**
     * Creates a failed API response with data and a default error message.
     *
     * @param data the error-related data
     * @return a failed API response
     */
    public static <T> APIResponse<T> failed(T data) {
        return APIResponse.<T>builder()
                .success(false)
                .data(data)
                .build();
    }

    /**
     * Creates a failed API response indicating the data was not found.
     *
     * @return a not-found API response
     */
    public static <T> APIResponse<T> notFound() {
        return APIResponse.<T>builder()
                .success(false)
                .message("Data not found")
                .build();
    }
}

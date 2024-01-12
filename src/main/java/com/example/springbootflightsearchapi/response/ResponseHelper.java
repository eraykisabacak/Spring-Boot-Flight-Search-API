package com.example.springbootflightsearchapi.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHelper {
    public static <T> ResponseEntity<ApiResponse<T>> apiResponse(HttpStatus status, String message, T data) {
        ApiResponse<T> apiResponse = new ApiResponse<>("success", status.value(), message, data);
        return new ResponseEntity<>(apiResponse, status);
    }
}

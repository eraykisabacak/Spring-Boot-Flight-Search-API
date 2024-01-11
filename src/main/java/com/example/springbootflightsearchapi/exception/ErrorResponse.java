package com.example.springbootflightsearchapi.exception;

public record ErrorResponse(String message, int statusCode) {
}

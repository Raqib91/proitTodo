package com.proit.todo.app.backend.utils;

import com.proit.todo.app.backend.entities.ApiResponse;

import java.util.Date;

public class ApiResponseBuilder {
    public static ApiResponse buildSuccessResponse(String message) {
        return ApiResponse.builder()
                .message(message)
                .success(true)
                .time(new Date())
                .build();
    }

    public static ApiResponse buildFailResponse(RuntimeException exception) {
        return ApiResponse.builder()
                .message(exception.getMessage())
                .success(false)
                .time(new Date())
                .build();
    }
}

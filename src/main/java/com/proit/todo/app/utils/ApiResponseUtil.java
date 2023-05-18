package com.proit.todo.app.utils;

import com.proit.todo.app.entities.ApiResponse;

import java.util.Date;

public class ApiResponseUtil {
    public static ApiResponse buildResponse(String message, boolean success) {
        return ApiResponse.builder()
                .message(message)
                .success(success)
                .time(new Date())
                .build();
    }
}

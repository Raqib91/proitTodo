package com.proit.todo.app.utils;

import com.proit.todo.app.models.ApiResponse;

import java.util.Date;

/**
 * @author raqib91
 */
public class ApiResponseUtil {
    public static ApiResponse buildResponse(String message, boolean success) {
        return ApiResponse.builder()
                .message(message)
                .success(success)
                .timestamp(new Date())
                .build();
    }
}

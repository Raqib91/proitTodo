package com.proit.todo.app.exceptions;

import com.proit.todo.app.models.ApiResponse;
import com.proit.todo.app.utils.ApiResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author raqib91
 */
@RestControllerAdvice
@CrossOrigin(origins = "*")
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return new ResponseEntity<>(ApiResponseUtil.buildResponse(exception.getMessage(), false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ResourceNotModifiedException.class)
    public ResponseEntity<ApiResponse> handleResourceNotModifiedException(ResourceNotModifiedException exception) {
        return new ResponseEntity<>(ApiResponseUtil.buildResponse(exception.getMessage(), false), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UserNotAuthorizedException.class)
    public ResponseEntity<ApiResponse> handleUserNotAuthorizedException(UserNotAuthorizedException exception) {
        return new ResponseEntity<>(ApiResponseUtil.buildResponse(exception.getMessage(), false), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = UserNotAuthenticatedException.class)
    public ResponseEntity<ApiResponse> handleUserNotAuthenticatedException(UserNotAuthenticatedException exception) {
        return new ResponseEntity<>(ApiResponseUtil.buildResponse(exception.getMessage(), false), HttpStatus.FORBIDDEN);
    }
}

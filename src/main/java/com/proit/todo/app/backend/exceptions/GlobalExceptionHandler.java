package com.proit.todo.app.backend.exceptions;

import com.proit.todo.app.backend.entities.ApiResponse;
import com.proit.todo.app.backend.utils.ApiResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@CrossOrigin(origins = "*")
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ApiResponse> handleNullPointerException(NullPointerException exception) {
        return new ResponseEntity<>(ApiResponseBuilder.buildFailResponse(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return new ResponseEntity<>(ApiResponseBuilder.buildFailResponse(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ResourceNotModifiedException.class)
    public ResponseEntity<ApiResponse> handleResourceNotModifiedException(ResourceNotModifiedException exception) {
        return new ResponseEntity<>(ApiResponseBuilder.buildFailResponse(exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

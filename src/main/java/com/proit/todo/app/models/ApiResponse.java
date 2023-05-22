package com.proit.todo.app.models;

import lombok.*;

import java.util.Date;

/**
 * @author raqib91
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ApiResponse {
    private String message;
    private boolean success;
    private Date timestamp;
}

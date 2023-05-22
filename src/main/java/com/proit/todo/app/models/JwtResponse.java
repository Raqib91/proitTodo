package com.proit.todo.app.models;

import lombok.*;

/**
 * @author raqib91
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class JwtResponse {
    private String token;
}

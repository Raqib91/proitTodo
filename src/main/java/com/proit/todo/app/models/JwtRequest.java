package com.proit.todo.app.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class JwtRequest {
    private String username;
    private String password;
}

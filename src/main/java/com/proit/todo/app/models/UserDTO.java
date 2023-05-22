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
public class UserDTO {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
}

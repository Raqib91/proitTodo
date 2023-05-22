package com.proit.todo.app.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * @author raqib91
 */
@Setter
@Getter
public class UserNotAuthenticatedException extends RuntimeException {
    public UserNotAuthenticatedException() {
        super("USER NOT AUTHENTICATED");
    }
}

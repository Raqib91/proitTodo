package com.proit.todo.app.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * @author raqib91
 */
@Setter
@Getter
public class UserNotAuthorizedException extends RuntimeException {
    public UserNotAuthorizedException() {
        super("USER NOT AUTHORIZED");
    }
}

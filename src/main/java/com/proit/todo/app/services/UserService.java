package com.proit.todo.app.services;

import com.proit.todo.app.entities.User;
import com.proit.todo.app.models.UserDTO;

/**
 * @author raqib91
 */
public interface UserService {
    User create(UserDTO userDTO);

    void modify(User user);

    User getByUserName(String username);
}

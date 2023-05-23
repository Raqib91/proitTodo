package com.proit.todo.app.services.impl;

import com.proit.todo.app.entities.User;
import com.proit.todo.app.exceptions.UserNotAuthenticatedException;
import com.proit.todo.app.exceptions.UserNotAuthorizedException;
import com.proit.todo.app.models.UserDTO;
import com.proit.todo.app.repositories.UserRepository;
import com.proit.todo.app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author raqib91
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user != null)
            return null;

        return userRepository.save(User.builder()
                .firstname(userDTO.getFirstname())
                .lastname(userDTO.getLastname())
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build());
    }

    @Override
    public User modify(String username, UserDTO userDTO) {
        User user = getByUserName(username);
        if (!user.getUsername().equals(userDTO.getUsername()))
            throw new UserNotAuthorizedException();

        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty())
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getByUserName(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UserNotAuthenticatedException();
        return user;
    }
}

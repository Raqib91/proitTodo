package com.proit.todo.app.services.impl;

import com.proit.todo.app.entities.User;
import com.proit.todo.app.exceptions.UserNotAuthenticatedException;
import com.proit.todo.app.exceptions.UserNotAuthorizedException;
import com.proit.todo.app.models.UserDTO;
import com.proit.todo.app.repositories.UserRepository;
import com.proit.todo.app.security.JwtUtil;
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
    private final JwtUtil jwtUtil;

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
    public void modify(User user) {
        userRepository.save(user);
    }

    @Override
    public User getByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByToken(String token) {
        User user = getByUserName(getUserNameByToken(token));
        if (user == null)
            throw new UserNotAuthenticatedException();
        return user;
    }

    private String getUserNameByToken(String token) {
        String username = jwtUtil.extractUsername(token);
        if (username == null)
            throw new UserNotAuthorizedException();
        return username;
    }
}

package com.proit.todo.app.services.impl;

import com.proit.todo.app.entities.User;
import com.proit.todo.app.exceptions.ResourceNotFoundException;
import com.proit.todo.app.repositories.UserRepository;
import com.proit.todo.app.utils.ResourceType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author raqib91
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new ResourceNotFoundException(ResourceType.USER.name(), "USERNAME", username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), new ArrayList<>());
    }
}

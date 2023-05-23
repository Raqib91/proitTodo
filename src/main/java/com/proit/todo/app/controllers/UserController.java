package com.proit.todo.app.controllers;

import com.proit.todo.app.entities.User;
import com.proit.todo.app.exceptions.ResourceNotModifiedException;
import com.proit.todo.app.exceptions.UserNotAuthenticatedException;
import com.proit.todo.app.exceptions.UserNotAuthorizedException;
import com.proit.todo.app.models.JwtRequest;
import com.proit.todo.app.models.JwtResponse;
import com.proit.todo.app.models.UserDTO;
import com.proit.todo.app.security.JwtUtil;
import com.proit.todo.app.services.UserService;
import com.proit.todo.app.utils.ApiResponseUtil;
import com.proit.todo.app.utils.OperationType;
import com.proit.todo.app.utils.ResourceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author raqib91
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping(path = "/public/register")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        User user = userService.create(userDTO);
        if (user == null)
            throw new ResourceNotModifiedException(OperationType.CREATE.name(), ResourceType.USER.name(), null, 0);
        return new ResponseEntity<>(ApiResponseUtil.buildResponse("USER CREATED", true), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return ResponseEntity.ok(userService.getByUserName(username));
    }

    @PutMapping
    public ResponseEntity<?> modifyUser(@RequestBody UserDTO userDTO, Principal principal) {
        log.info("User " + userDTO);
        String username = principal.getName();
        if (username == null)
            throw new UserNotAuthorizedException();
        User user = userService.modify(username, userDTO);
        if (user == null)
            throw new ResourceNotModifiedException(OperationType.MODIFY.name(), ResourceType.USER.name(), "USERNAME", username);
        return new ResponseEntity<>(ApiResponseUtil.buildResponse("USER MODIFIED WITH USERNAME: " + username, true), HttpStatus.OK);
    }

    @PostMapping(path = "/public/login")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),
                            jwtRequest.getPassword()));
        } catch (Exception e) {
            throw new UserNotAuthenticatedException();
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}

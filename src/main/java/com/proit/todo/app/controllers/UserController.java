package com.proit.todo.app.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author raqib91
 */
@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins = "*")
public class UserController {
}

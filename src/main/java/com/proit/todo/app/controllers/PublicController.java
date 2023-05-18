package com.proit.todo.app.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author raqib91
 */
@RestController
@RequestMapping(path = "/public")
@CrossOrigin(origins = "*")
public class PublicController {
    @GetMapping(path = "/login")
    public String login() {
        return "Hello there";
    }
}

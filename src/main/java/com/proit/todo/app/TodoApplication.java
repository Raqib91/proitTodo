package com.proit.todo.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

/**
 * @author raqib91
 */
@SpringBootApplication
@Slf4j
public class TodoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
        log.info("================ TODO APP STARTED SUCCESSFULLY AT {} ================", new Date());
    }
}

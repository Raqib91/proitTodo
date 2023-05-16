package com.proit.todo.app.backend.controllers;

import com.proit.todo.app.backend.entities.Todo;
import com.proit.todo.app.backend.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author raqib91
 */
@RestController
@RequestMapping(path = "/todos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public void createTodo(@RequestBody Todo todo) {
        todoService.createOrUpdate(todo);
    }

    @PutMapping
    public void updateTodo(@RequestBody Todo todo) {
        todoService.createOrUpdate(todo);
    }

    @GetMapping
    public List<Todo> getAllTodo() {
        return todoService.getAll();
    }

    @GetMapping(path = "/{id}")
    public Todo getTodoById(@PathVariable(name = "id") long id) {
        return todoService.getById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTodoById(@PathVariable(name = "id") long id) {
        todoService.delete(id);
    }
}

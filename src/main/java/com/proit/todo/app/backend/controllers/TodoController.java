package com.proit.todo.app.backend.controllers;

import com.proit.todo.app.backend.entities.ApiResponse;
import com.proit.todo.app.backend.entities.Todo;
import com.proit.todo.app.backend.exceptions.ResourceNotModifiedException;
import com.proit.todo.app.backend.services.TodoService;
import com.proit.todo.app.backend.utils.ApiResponseBuilder;
import com.proit.todo.app.backend.utils.OperationType;
import com.proit.todo.app.backend.utils.ResourceType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse> createTodo(@RequestBody Todo todo) {
        Todo newTodo = todoService.createOrModify(todo);
        if (newTodo == null)
            throw new ResourceNotModifiedException(OperationType.CREATE.name(), ResourceType.TODO.name(), null, 0);
        return new ResponseEntity<>(ApiResponseBuilder.buildSuccessResponse("TODO CREATED"), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateTodo(@RequestBody Todo todo) {
        Todo newTodo = todoService.createOrModify(todo);
        if (newTodo == null)
            throw new ResourceNotModifiedException(OperationType.MODIFY.name(), ResourceType.TODO.name(), "ID", todo.getId());
        return new ResponseEntity<>(ApiResponseBuilder.buildSuccessResponse("TODO MODIFIED WITH ID: " + todo.getId()), HttpStatus.OK);
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
    public ResponseEntity<ApiResponse> deleteTodoById(@PathVariable(name = "id") long id) {
        todoService.delete(id);
        return new ResponseEntity<>(ApiResponseBuilder.buildSuccessResponse("TODO DELETED WITH ID: " + id), HttpStatus.OK);
    }
}

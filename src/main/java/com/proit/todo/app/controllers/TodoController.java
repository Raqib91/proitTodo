package com.proit.todo.app.controllers;

import com.proit.todo.app.entities.ApiResponse;
import com.proit.todo.app.entities.Todo;
import com.proit.todo.app.exceptions.ResourceNotModifiedException;
import com.proit.todo.app.services.TodoService;
import com.proit.todo.app.utils.ApiResponseUtil;
import com.proit.todo.app.utils.OperationType;
import com.proit.todo.app.utils.ResourceType;
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
        return new ResponseEntity<>(ApiResponseUtil.buildResponse("TODO CREATED", true), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateTodo(@RequestBody Todo todo) {
        Todo newTodo = todoService.createOrModify(todo);
        if (newTodo == null)
            throw new ResourceNotModifiedException(OperationType.MODIFY.name(), ResourceType.TODO.name(), "ID", todo.getId());
        return new ResponseEntity<>(ApiResponseUtil.buildResponse("TODO MODIFIED WITH ID: " + todo.getId(), true), HttpStatus.OK);
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
        return new ResponseEntity<>(ApiResponseUtil.buildResponse("TODO DELETED WITH ID: " + id, true), HttpStatus.OK);
    }
}

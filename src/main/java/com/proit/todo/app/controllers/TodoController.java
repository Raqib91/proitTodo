package com.proit.todo.app.controllers;

import com.proit.todo.app.entities.Todo;
import com.proit.todo.app.exceptions.ResourceNotFoundException;
import com.proit.todo.app.exceptions.ResourceNotModifiedException;
import com.proit.todo.app.models.ApiResponse;
import com.proit.todo.app.models.TodoDTO;
import com.proit.todo.app.security.JwtUtil;
import com.proit.todo.app.services.TodoService;
import com.proit.todo.app.utils.ApiResponseUtil;
import com.proit.todo.app.utils.OperationType;
import com.proit.todo.app.utils.ResourceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author raqib91
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/todos")
@RequiredArgsConstructor
@Slf4j
public class TodoController {
    private final TodoService todoService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<ApiResponse> createTodo(@RequestHeader(name = "Token") String token,
                                                  @RequestBody TodoDTO todoDTO) {
        log.info("TODO RECEIVED: {}", todoDTO);
        Todo newTodo = todoService.create(jwtUtil.extractUsername(token), todoDTO);
        if (newTodo == null)
            throw new ResourceNotModifiedException(OperationType.CREATE.name(), ResourceType.TODO.name(), null, 0);
        return new ResponseEntity<>(ApiResponseUtil.buildResponse("TODO CREATED", true), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateTodo(@RequestHeader(name = "Token") String token,
                                                  @RequestBody TodoDTO todoDTO) {
        Todo newTodo = todoService.modify(jwtUtil.extractUsername(token), todoDTO);
        if (newTodo == null)
            throw new ResourceNotModifiedException(OperationType.MODIFY.name(), ResourceType.TODO.name(), "ID", todoDTO.getId());
        return new ResponseEntity<>(ApiResponseUtil.buildResponse("TODO MODIFIED WITH ID: " + todoDTO.getId(), true), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodo(@RequestHeader(name = "Token") String token) {
        log.info("Token received " + token);
        log.info("Requestor " + jwtUtil.extractUsername(token));
        List<Todo> todoList = todoService.getAllByUsername(jwtUtil.extractUsername(token));
        if (todoList == null || todoList.isEmpty())
            throw new ResourceNotFoundException(ResourceType.TODO.name(), null, 0L);
        return ResponseEntity.ok(todoList);
    }

    @GetMapping(path = "/{id}")
    public Todo getTodoById(@RequestHeader(name = "Token") String token,
                            @PathVariable(name = "id") long id) {
        return todoService.getById(id);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteTodoById(TodoDTO todoDTO) {
        todoService.delete(todoDTO.getId());
        return new ResponseEntity<>(ApiResponseUtil.buildResponse("TODO DELETED WITH ID: " + todoDTO.getId(), true), HttpStatus.OK);
    }
}

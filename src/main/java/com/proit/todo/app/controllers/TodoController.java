package com.proit.todo.app.controllers;

import com.proit.todo.app.entities.Todo;
import com.proit.todo.app.exceptions.ResourceNotFoundException;
import com.proit.todo.app.exceptions.ResourceNotModifiedException;
import com.proit.todo.app.exceptions.UserNotAuthorizedException;
import com.proit.todo.app.models.ApiResponse;
import com.proit.todo.app.models.TodoDTO;
import com.proit.todo.app.services.TodoService;
import com.proit.todo.app.utils.ApiResponseUtil;
import com.proit.todo.app.utils.OperationType;
import com.proit.todo.app.utils.ResourceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @PostMapping
    public ResponseEntity<ApiResponse> createTodo(@RequestBody TodoDTO todoDTO, Principal principal) {
        String username = principal.getName();
        if (username == null)
            throw new UserNotAuthorizedException();
        Todo newTodo = todoService.create(username, todoDTO);
        if (newTodo == null)
            throw new ResourceNotModifiedException(OperationType.CREATE.name(), ResourceType.TODO.name(), null, 0);
        return new ResponseEntity<>(ApiResponseUtil.buildResponse("TODO CREATED", true), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateTodo(@RequestBody TodoDTO todoDTO, Principal principal) {
        String username = principal.getName();
        if (username == null)
            throw new UserNotAuthorizedException();
        Todo newTodo = todoService.modify(username, todoDTO);
        if (newTodo == null)
            throw new ResourceNotModifiedException(OperationType.MODIFY.name(), ResourceType.TODO.name(), "ID", todoDTO.getId());
        return new ResponseEntity<>(ApiResponseUtil.buildResponse("TODO MODIFIED WITH ID: " + todoDTO.getId(), true), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodo(Principal principal) {
        String username = principal.getName();
        if (username == null)
            throw new UserNotAuthorizedException();
        List<Todo> todoList = todoService.getAllByUser(username);
        if (todoList == null || todoList.isEmpty())
            throw new ResourceNotFoundException(ResourceType.TODO.name(), null, 0L);
        return ResponseEntity.ok(todoList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable(name = "id") long id, Principal principal) {
        String username = principal.getName();
        if (username == null)
            throw new UserNotAuthorizedException();
        return ResponseEntity.ok(todoService.getById(username, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> deleteTodoById(@PathVariable(name = "id") long id, Principal principal) {
        String username = principal.getName();
        if (username == null)
            throw new UserNotAuthorizedException();
        todoService.delete(username, id);
        return new ResponseEntity<>(ApiResponseUtil.buildResponse("TODO DELETED WITH ID: " + id, true), HttpStatus.OK);
    }
}

package com.proit.todo.app.services;

import com.proit.todo.app.entities.Todo;
import com.proit.todo.app.models.TodoDTO;

import java.util.List;

/**
 * @author raqib91
 */
public interface TodoService {
    Todo create(String token, TodoDTO todoDTO);

    Todo modify(String token, TodoDTO todoDTO);

    Todo getById(String token, long id);

    List<Todo> getAllByToken(String token);

    void delete(String token, long id);
}

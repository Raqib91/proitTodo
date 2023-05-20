package com.proit.todo.app.services;

import com.proit.todo.app.entities.Todo;
import com.proit.todo.app.models.TodoDTO;

import java.util.List;

/**
 * @author raqib91
 */
public interface TodoService {
    Todo create(String username, TodoDTO todoDTO);

    Todo modify(String username, TodoDTO todoDTO);

    Todo getById(long id);

    List<Todo> getAllByUsername(String username);

    void delete(long id);
}

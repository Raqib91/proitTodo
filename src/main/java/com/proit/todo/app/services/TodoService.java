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

    Todo getById(String username, long id);

    List<Todo> getAllByUser(String username);

    void delete(String username, long id);
}

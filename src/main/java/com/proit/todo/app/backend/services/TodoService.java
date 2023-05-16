package com.proit.todo.app.backend.services;

import com.proit.todo.app.backend.entities.Todo;

import java.util.List;

/**
 * @author raqib91
 */
public interface TodoService {
    void createOrUpdate(Todo todo);

    Todo getById(long id);

    List<Todo> getAll();

    void delete(long id);
}

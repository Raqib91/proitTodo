package com.proit.todo.app.services;

import com.proit.todo.app.entities.Todo;

import java.util.List;

/**
 * @author raqib91
 */
public interface TodoService {
    Todo createOrModify(Todo todo);

    Todo getById(long id);

    List<Todo> getAll();

    void delete(long id);
}

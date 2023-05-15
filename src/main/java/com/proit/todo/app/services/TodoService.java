package com.proit.todo.app.services;

import com.proit.todo.app.entities.Todo;

import java.util.List;

public interface TodoService {
    void createOrUpdate(Todo todo);

    Todo getById(long id);

    List<Todo> getAll();

    void delete(long id);
}

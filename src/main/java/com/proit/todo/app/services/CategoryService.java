package com.proit.todo.app.services;

import com.proit.todo.app.entities.Category;

import java.util.List;

public interface CategoryService {
    Category createOrUpdate(Category category);

    Category getById(long id);

    Category getByTitle(String title);

    List<Category> getAll();

    void delete(long id);
}

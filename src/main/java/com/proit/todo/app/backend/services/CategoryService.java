package com.proit.todo.app.backend.services;

import com.proit.todo.app.backend.entities.Category;

import java.util.List;

/**
 * @author raqib91
 */
public interface CategoryService {
    Category getDefaultCategory();

    Category createOrUpdate(Category category);

    Category getById(long id);

    Category getByTitle(String title);

    List<Category> getAll();

    void delete(long id);
}

package com.proit.todo.app.services;

import com.proit.todo.app.entities.Category;

import java.util.List;

/**
 * @author raqib91
 */
public interface CategoryService {
    Category getDefaultCategory();

    Category createOrModify(Category category);

    Category getById(long id);

    Category getByTitle(String title);

    List<Category> getAll();

    void delete(long id);
}

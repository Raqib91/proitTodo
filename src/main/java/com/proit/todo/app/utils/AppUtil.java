package com.proit.todo.app.utils;

import com.proit.todo.app.entities.Category;
import com.proit.todo.app.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppUtil implements CommandLineRunner {
    private final CategoryService categoryService;

    public static Category defaultCategory;

    @Override
    public void run(String... args) {
        defaultCategory = categoryService.getByTitle(Constants.DEFAULT_CATEGORY);
        if (defaultCategory == null)
            defaultCategory = categoryService.createOrUpdate(new Category());
    }
}

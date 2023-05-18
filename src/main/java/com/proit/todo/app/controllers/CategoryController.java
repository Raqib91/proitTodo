package com.proit.todo.app.controllers;

import com.proit.todo.app.entities.Category;
import com.proit.todo.app.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author raqib91
 */
@RestController
@RequestMapping(path = "/categories")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public void createCategory(@RequestBody Category category) {
        categoryService.createOrModify(category);
    }

    @PutMapping
    public void updateCategory(@RequestBody Category category) {
        categoryService.createOrModify(category);
    }

    @GetMapping
    public List<Category> getAllCategory() {
        return categoryService.getAll();
    }

    @GetMapping(path = "/{id}")
    public Category getCategoryById(@PathVariable(name = "id") long id) {
        return categoryService.getById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCategoryById(@PathVariable(name = "id") long id) {
        categoryService.delete(id);
    }
}

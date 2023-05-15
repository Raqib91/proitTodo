package com.proit.todo.app.services.impls;

import com.proit.todo.app.entities.Category;
import com.proit.todo.app.repositories.CategoryRepository;
import com.proit.todo.app.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category createOrUpdate(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    @Override
    public Category getByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void delete(long id) {
        categoryRepository.deleteById(id);
    }
}

package com.proit.todo.app.backend.services.impls;

import com.proit.todo.app.backend.entities.Category;
import com.proit.todo.app.backend.repositories.CategoryRepository;
import com.proit.todo.app.backend.services.CategoryService;
import com.proit.todo.app.backend.utils.Constants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

/**
 * @author raqib91
 */
@Service
@Getter
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    private Category defaultCategory;

    @PostConstruct
    private void init() {
        defaultCategory = getByTitle(Constants.DEFAULT_CATEGORY_TITLE);
        if (defaultCategory == null)
            defaultCategory = createOrUpdate(new Category());
    }

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

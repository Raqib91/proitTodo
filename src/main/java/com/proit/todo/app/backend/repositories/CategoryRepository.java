package com.proit.todo.app.backend.repositories;

import com.proit.todo.app.backend.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author raqib91
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String title);
}

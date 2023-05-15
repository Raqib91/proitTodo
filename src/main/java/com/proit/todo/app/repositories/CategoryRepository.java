package com.proit.todo.app.repositories;

import com.proit.todo.app.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String title);
}
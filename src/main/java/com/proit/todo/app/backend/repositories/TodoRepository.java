package com.proit.todo.app.backend.repositories;

import com.proit.todo.app.backend.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author raqib91
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {
}

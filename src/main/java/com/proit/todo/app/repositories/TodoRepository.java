package com.proit.todo.app.repositories;

import com.proit.todo.app.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}

package com.proit.todo.app.backend.repositories;

import com.proit.todo.app.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author raqib91
 */
public interface UserRepository extends JpaRepository<User, Long> {
}

package com.proit.todo.app.repositories;

import com.proit.todo.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author raqib91
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

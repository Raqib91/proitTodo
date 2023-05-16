package com.proit.todo.app.backend.services.impls;

import com.proit.todo.app.backend.entities.Todo;
import com.proit.todo.app.backend.repositories.TodoRepository;
import com.proit.todo.app.backend.services.CategoryService;
import com.proit.todo.app.backend.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author raqib91
 */
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final CategoryService categoryService;

    @Override
    public void createOrUpdate(Todo todo) {
        if (todo.getCategory() == null)
            todo.setCategory(categoryService.getDefaultCategory());
        todoRepository.save(todo);
    }

    @Override
    public Todo getById(long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.orElse(null);
    }

    @Override
    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    @Override
    public void delete(long id) {
        todoRepository.deleteById(id);
    }
}

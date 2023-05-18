package com.proit.todo.app.services.impls;

import com.proit.todo.app.entities.Todo;
import com.proit.todo.app.exceptions.ResourceNotFoundException;
import com.proit.todo.app.repositories.TodoRepository;
import com.proit.todo.app.services.CategoryService;
import com.proit.todo.app.services.TodoService;
import com.proit.todo.app.utils.ResourceType;
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
    public Todo createOrModify(Todo todo) {
        if (todo.getCategory() == null)
            todo.setCategory(categoryService.getDefaultCategory());
        return todoRepository.save(todo);
    }

    @Override
    public Todo getById(long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.orElseThrow(() -> new ResourceNotFoundException(ResourceType.TODO.name(), "ID", id));
    }

    @Override
    public List<Todo> getAll() {
        List<Todo> todoList = todoRepository.findAll();
        if (todoList.isEmpty())
            throw new ResourceNotFoundException(ResourceType.TODO.name(), null, 0);
        return todoList;
    }

    @Override
    public void delete(long id) {
        todoRepository.deleteById(id);
    }
}
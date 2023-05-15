package com.proit.todo.app.services.impls;

import com.proit.todo.app.entities.Category;
import com.proit.todo.app.entities.Todo;
import com.proit.todo.app.repositories.TodoRepository;
import com.proit.todo.app.services.TodoService;
import com.proit.todo.app.utils.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    @Override
    public void createOrUpdate(Todo todo) {
        Category category = todo.getCategory();
        if (category == null)
            todo.setCategory(AppUtil.defaultCategory);
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

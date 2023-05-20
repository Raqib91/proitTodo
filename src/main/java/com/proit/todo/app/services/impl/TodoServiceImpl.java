package com.proit.todo.app.services.impl;

import com.proit.todo.app.entities.Todo;
import com.proit.todo.app.entities.User;
import com.proit.todo.app.exceptions.ResourceNotFoundException;
import com.proit.todo.app.models.TodoDTO;
import com.proit.todo.app.repositories.TodoRepository;
import com.proit.todo.app.services.TodoService;
import com.proit.todo.app.services.UserService;
import com.proit.todo.app.utils.ResourceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author raqib91
 */
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final UserService userService;

    @Override
    public Todo create(String username, TodoDTO todoDTO) {
        User user = getUserByUsername(username);
        if (user == null)
            return null;

        if (user.getTodoList() == null)
            user.setTodoList(new ArrayList<>());

        Todo todo = Todo.builder()
                .title(todoDTO.getTitle())
                .description(todoDTO.getDescription())
                .deadline(todoDTO.getDeadline())
                .build();

        user.getTodoList().add(todo);
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    @Override
    public Todo modify(String username, TodoDTO todoDTO) {
        User user = getUserByUsername(username);
        if (user == null || user.getTodoList() == null || user.getTodoList().isEmpty())
            return null;

        Todo todo = getById(todoDTO.getId());
        if (todo == null)
            return null;

        todo.setTitle(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());
        todo.setDeadline(todoDTO.getDeadline());
        return todoRepository.save(todo);
    }

    @Override
    public Todo getById(long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.orElseThrow(() -> new ResourceNotFoundException(ResourceType.TODO.name(), "ID", id));
    }

    @Override
    public List<Todo> getAllByUsername(String username) {
        return getUserByUsername(username).getTodoList();
    }

    @Override
    public void delete(long id) {
        todoRepository.deleteById(id);
    }

    private User getUserByUsername(String username) {
        return userService.getByUserName(username);
    }
}

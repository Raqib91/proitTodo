package com.proit.todo.app.services.impl;

import com.proit.todo.app.entities.Todo;
import com.proit.todo.app.entities.User;
import com.proit.todo.app.exceptions.ResourceNotFoundException;
import com.proit.todo.app.exceptions.ResourceNotModifiedException;
import com.proit.todo.app.models.TodoDTO;
import com.proit.todo.app.repositories.TodoRepository;
import com.proit.todo.app.services.TodoService;
import com.proit.todo.app.services.UserService;
import com.proit.todo.app.utils.Constants;
import com.proit.todo.app.utils.OperationType;
import com.proit.todo.app.utils.ResourceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        User user = userService.getByUserName(username);
        if (user.getTodoList() == null)
            user.setTodoList(new ArrayList<>());

        Todo todo = Todo.builder()
                .title(todoDTO.getTitle())
                .description(todoDTO.getDescription())
                .deadline(todoDTO.getDeadline() == null ?
                        new Date() : todoDTO.getDeadline())
                .build();

        user.getTodoList().add(todo);
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    @Override
    public Todo modify(String username, TodoDTO todoDTO) {
        User user = userService.getByUserName(username);
        if (user.getTodoList() == null || user.getTodoList().isEmpty())
            return null;

        Todo todo = getById(username, todoDTO.getId());
        if (todo == null)
            return null;
        todo.setTitle(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());
        todo.setDeadline(todoDTO.getDeadline() == null ?
                new Date() : todoDTO.getDeadline());
        return todoRepository.save(todo);
    }

    @Override
    public Todo getById(String username, long id) {
        User user = userService.getByUserName(username);
        if (user.getTodoList() == null || user.getTodoList().isEmpty())
            throw new ResourceNotFoundException(ResourceType.TODO.name(), "ID", id);

        return user.getTodoList().stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(ResourceType.TODO.name(), "ID", id));
    }

    @Override
    public List<Todo> getAllByUser(String username) {
        Date today = new Date();
        List<Todo> todoList = userService.getByUserName(username).getTodoList();
        if (todoList != null && !todoList.isEmpty()) {
            for (int i = 0; i < todoList.size(); i++) {
                try {
                    Todo todo = todoList.get(i);
                    if (today.getTime() > (todo.getDeadline().getTime() + Constants.TWELVE_HOURS_IN_MILLIS)) {
                        todo.setStatus(false);
                        todoList.set(i, todo);
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return todoList;
    }

    @Override
    public void delete(String username, long id) {
        Todo todo = getById(username, id);
        if (todo == null)
            throw new ResourceNotModifiedException(OperationType.DELETE.name(), ResourceType.TODO.name(), null, 0L);
        todoRepository.deleteById(id);
    }
}

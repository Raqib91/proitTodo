import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Todo } from '../models/todo';

@Injectable({
  providedIn: 'root',
})
export class HomeService {
  url = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getTodos() {
    return this.http.get<any>(`${this.url}/todos`);
  }

  getTodoById(id: number) {
    return this.http.get<any>(`${this.url}/todos/` + id);
  }

  saveTodo(todo: Todo) {
    return this.http.post(`${this.url}/todos`, todo);
  }

  updateTodo(todo: Todo) {
    return this.http.put(`${this.url}/todos`, todo);
  }

  deleteTodoById(id: number) {
    return this.http.delete(`${this.url}/todos/` + id);
  }
}

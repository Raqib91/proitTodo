import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Todo } from 'src/app/models/todo';
import { HomeService } from 'src/app/services/home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  todoDetail!: FormGroup;
  todo: Todo = new Todo();
  todoList: Todo[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private homeService: HomeService,
    private router: Router
  ) {}

  ngOnInit(): void {
    console.log(this.todo);

    this.fetchTodos();
    this.todoDetail = this.formBuilder.group({
      id: [''],
      title: [''],
      description: [''],
      deadline: [''],
    });
  }

  createTodo() {
    console.log(this.todoDetail);
    this.todo.id = this.todoDetail.value.id;
    this.todo.title = this.todoDetail.value.title;
    this.todo.description = this.todoDetail.value.description;
    this.todo.deadline = this.todoDetail.value.deadline;

    this.homeService.saveTodo(this.todo).subscribe(
      (response: any) => {
        console.log(response);
        alert('Todo created successfully');
        this.fetchTodos();
      },
      (error) => {
        alert('COULD NOT CREATE TODO');
      }
    );
  }

  editTodo(id: number) {
    console.log(id);
    this.fetchTodoById(id);
  }

  updateData() {
    console.log(this.todoDetail);
    this.todo.id = this.todoDetail.value.id;
    this.todo.title = this.todoDetail.value.title;
    this.todo.description = this.todoDetail.value.description;
    this.todo.deadline = this.todoDetail.value.deadline;

    this.homeService.updateTodo(this.todo).subscribe(
      (response: any) => {
        console.log(response);
        alert('Todo updated successfully');
        this.fetchTodos();
      },
      (error) => {
        alert('COULD NOT UPDATE TODO');
      }
    );
  }

  fetchTodos() {
    this.homeService.getTodos().subscribe(
      (response: any) => {
        this.todoList = response;
        console.log(typeof this.todoList[0]);
      },
      (error) => {
        alert('COULD NOT FETCH TODOS');
      }
    );
  }

  fetchTodoById(id: number) {
    this.homeService.getTodoById(id).subscribe(
      (response: Todo) => {
        console.log(response);
        this.todoDetail.controls['id'].setValue(response.id);
        this.todoDetail.controls['title'].setValue(response.title);
        this.todoDetail.controls['description'].setValue(response.description);
        this.todoDetail.controls['deadline'].setValue(response.deadline);
      },
      (error) => {
        alert('COULD NOT FETCH TODO WITH ID: ' + id);
      }
    );
  }
}

import { Component } from '@angular/core';
import { formatDate } from '@angular/common';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Todo } from 'src/app/models/todo';
import { HomeService } from 'src/app/services/home.service';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  todoDetail!: FormGroup;
  todo: Todo = new Todo();
  todoList: Todo[] = [];
  noTodo: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private homeService: HomeService,
    private loginService: LoginService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.fetchTodos();
  }

  createTodo() {
    if (!this.loginService.checkForLogout()) {
      console.log(this.todoDetail);

      if (this.validateForm()) {
        this.todo.id = this.todoDetail.value.id;
        this.todo.title = this.todoDetail.value.title;
        this.todo.description = this.todoDetail.value.description;
        this.todo.deadline = this.todoDetail.value.deadline;

        this.homeService.saveTodo(this.todo).subscribe(
          (response: any) => {
            this.fetchTodos();
            alert('Todo created successfully');
          },
          (error) => {
            if (error.status === 500) {
              alert('Could not create todo');
            } else if (error.status === 401 || error.status === 403) {
              alert('User not authorized');
              this.loginService.logout();
            } else {
              alert('Backend server is offline');
            }
          }
        );
      } else {
        alert('Please fill out the required fields and try again');
      }
    }
  }

  updateData() {
    if (!this.loginService.checkForLogout()) {
      console.log(this.todoDetail);

      if (this.validateForm()) {
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
            if (error.status === 500) {
              alert('Could not update todo');
            } else if (error.status === 401 || error.status === 403) {
              alert('User not authorized');
              this.loginService.logout();
            } else {
              alert('Backend server is offline');
            }
          }
        );
      } else {
        alert('Please fill out the required fields and try again');
      }
    }
  }

  fetchTodos() {
    if (!this.loginService.checkForLogout()) {
      this.clearForm();
      this.homeService.getTodos().subscribe(
        (response: any) => {
          this.todoList = response;
          this.noTodo = false;
        },
        (error) => {
          if (error.status === 404) {
            this.noTodo = true;
          } else if (error.status === 401 || error.status === 403) {
            alert('User not authorized');
            this.loginService.logout();
          } else {
            alert('Backend server is offline');
          }
        }
      );
    }
  }

  editTodo(id: number) {
    if (!this.loginService.checkForLogout()) {
      this.homeService.getTodoById(id).subscribe(
        (response: Todo) => {
          console.log(response);
          this.todoDetail.controls['id'].setValue(response.id);
          this.todoDetail.controls['title'].setValue(response.title);
          this.todoDetail.controls['description'].setValue(
            response.description
          );
          this.todoDetail.controls['deadline'].setValue(
            formatDate(response.deadline, 'yyyy-MM-dd', 'en-US')
          );
        },
        (error) => {
          if (error.status === 404) {
            alert('Todo not found');
          } else if (error.status === 401 || error.status === 403) {
            alert('User not authorized');
            this.loginService.logout();
          } else {
            alert('Backend server is offline');
          }
        }
      );
    }
  }

  deleteTodo(id: number) {
    if (!this.loginService.checkForLogout()) {
      this.homeService.deleteTodoById(id).subscribe(
        (response: any) => {
          alert('Todo deleted successfully');
          this.fetchTodos();
        },
        (error) => {
          if (error.status === 404) {
            alert('Todo cannot be deleted');
          } else if (error.status === 401 || error.status === 403) {
            alert('User not authorized');
            this.loginService.logout();
          }
          alert('Backend server is offline');
        }
      );
    }
  }

  clearForm() {
    this.todoDetail = this.formBuilder.group({
      id: [''],
      title: [''],
      description: [''],
      deadline: [''],
    });
  }

  validateForm(): boolean {
    if (
      this.todoDetail.value.title == null ||
      this.todoDetail.value.title === ''
    ) {
      return false;
    }
    return true;
  }
}

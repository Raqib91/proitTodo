import { Component } from '@angular/core';
import { HomeService } from 'src/app/services/home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  constructor(private homeService: HomeService) {}

  ngOnInit(): void {
    this.fetchTodos();
  }

  fetchTodos() {
    this.homeService.getTodos().subscribe(
      (response: any) => {
        console.log(response);
      },
      (error) => {
        alert('COULD NOT FETCH TODOS');
      }
    );
  }
}

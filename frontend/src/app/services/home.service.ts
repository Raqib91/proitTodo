import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class HomeService {
  url = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getTodos() {
    return this.http.get<any>(`${this.url}/todos`);
  }
}

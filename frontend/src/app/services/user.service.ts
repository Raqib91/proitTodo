import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  url = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getUserByUsername(username: string) {
    return this.http.get<any>(`${this.url}/users/` + username);
  }

  updateUser(user: User) {
    return this.http.put<any>(`${this.url}/users/`, user);
  }
}

import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  url = 'http://localhost:8080';
  loginTime: Date = new Date();

  constructor(private http: HttpClient, private router: Router) {}

  generateToken(credentials: any) {
    return this.http.post(`${this.url}/users/public/login`, credentials);
  }

  loginUser(token: string, username: string) {
    localStorage.setItem('token', token);
    localStorage.setItem('user', username);
    this.loginTime = new Date();
    return true;
  }

  isLoggedIn() {
    let token = localStorage.getItem('token');
    if (token == undefined || token === '' || token == null) {
      return false;
    } else {
      return true;
    }
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.router.navigate(['login']);
    return true;
  }

  getToken() {
    return localStorage.getItem('token');
  }

  checkForLogout() {
    var timeDiff: number = new Date().getTime() - this.loginTime.getTime();
    if (timeDiff > 10 * 60 * 1000) {
      this.logout();
      return true;
    }
    return false;
  }

  getUserName(): any {
    return localStorage.getItem('user');
  }
}

import { Router } from '@angular/router';
import { LoginService } from './../../services/login.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  credentials = {
    username: '',
    password: '',
  };

  constructor(private loginService: LoginService, private router: Router) {}

  onSubmit() {
    if (
      this.credentials.username == null ||
      this.credentials.password == null ||
      this.credentials.username === '' ||
      this.credentials.password === ''
    ) {
      alert('Enter valid username or password');
    } else {
      this.loginService.generateToken(this.credentials).subscribe(
        (response: any) => {
          console.log(response);
          this.loginService.loginUser(response.token, this.credentials.username);
          this.router.navigate(['home']);
        },
        (error) => {
          if (error.status === 403) {
            alert('Authenticated failed. Invalid username or password');
          } else {
            alert('Backend server is offline');
          }
        }
      );
    }
  }
}

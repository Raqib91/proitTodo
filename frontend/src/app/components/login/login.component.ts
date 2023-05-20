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
      console.log('Enter valid username or password');
    } else {
      console.log('Form submitted');
      this.loginService.generateToken(this.credentials).subscribe(
        (response: any) => {
          console.log(response);
          this.loginService.loginUser(response.token);
          this.router.navigate(['home']);
        },
        (error) => {
          alert('INVALID USERNAME OR PASSWORD');
        }
      );
    }
  }
}

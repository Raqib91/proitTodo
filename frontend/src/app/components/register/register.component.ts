import { Router } from '@angular/router';
import { RegisterService } from './../../services/register.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  credentials = {
    firstname: '',
    lastname: '',
    username: '',
    password: '',
  };

  constructor(
    private registerService: RegisterService,
    private router: Router
  ) {}

  onSubmit() {
    if (
      this.credentials.firstname == null ||
      this.credentials.username == null ||
      this.credentials.password == null ||
      this.credentials.firstname === '' ||
      this.credentials.username === '' ||
      this.credentials.password === ''
    ) {
      console.log('Enter valid data and try again');
    } else {
      console.log('Form submitted');
      this.registerService.registerUser(this.credentials).subscribe(
        (response: any) => {
          alert('USER REGISTERED');
          this.router.navigate(['login']);
        },
        (error) => {
          alert('COULD NOT REGISTER USER');
        }
      );
    }
  }
}

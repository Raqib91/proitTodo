import { LoginService } from './../services/login.service';
import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private loginService: LoginService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let newReq = req;
    let token = this.loginService.getToken();

    console.log('INTERCEPTOR: ', token);

    if (token != null) {
      newReq = newReq.clone({
        headers: req.headers.append('Token', token),
      });
    }
    return next.handle(newReq);
  }
}

import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from '../services/login.service';

@Injectable()
export class RequestInterceptor implements HttpInterceptor {
  constructor(private loginService: LoginService) {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    let token = this.loginService.getToken();

    console.log('INTERCEPTOR: ', typeof token);

    if (token != null) {
      request = request.clone({
        headers: request.headers.append('Token', token),
      });
      console.log(request);
    }
    return next.handle(request);
  }
}

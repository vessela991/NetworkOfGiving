// import { Injectable } from '@angular/core';
// import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
// import { AuthService } from './identity/services/auth.service';
// import { Observable } from 'rxjs';

// @Injectable({
//   providedIn: 'root'
// })
// export class TokenInterceptorService implements HttpInterceptor{

//   constructor(public authService: AuthService) { }
//   intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
//     request = request.clone({
//       setHeaders: {
//         Authorization: `Bearer ${localStorage.getItem('')}`
//       }
//     });
//    return next.handle(request);
//   }
// }

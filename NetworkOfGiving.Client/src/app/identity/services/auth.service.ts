import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment'
import { User } from 'src/models/User';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private options = {
    headers: new HttpHeaders({
      'Authorization': `Bearer ${this.getToken()}`
    }),
  };

  private loginPath = environment.apiUrl + 'identity/login';

  private userPath = environment.apiUrl + 'user';

  private registerPath = environment.apiUrl + 'identity/register';

  constructor(private httpClient: HttpClient) { }

  login(data): Observable<any> {
    return this.httpClient.post(this.loginPath, data);
  }

  register(data): Observable<any> {
    return this.httpClient.post(this.registerPath,data);
  }

  saveToken(token){
    localStorage.setItem('token',token);
  }

  logout(): void {
    localStorage.removeItem('token');
  }

  getToken(){
    return localStorage.getItem('token');
  }

  getUser() :Observable<User>{
    return this.httpClient.get<User>(this.userPath, this.options);
  }

  isLogged(){
    if(this.getToken()!=null){
      return true;
    }
    else return false;
  }
}

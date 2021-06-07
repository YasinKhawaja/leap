import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Login } from 'src/app/classes/login/login';

const httpOptions= {
  headers: new HttpHeaders(),
  withCredentials: true
};

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private loginUrl: string = '//localhost:8080/api/user/login';
  
  constructor(private http: HttpClient) {
  }
  

   public login(login: Login){
     var url = `${this.loginUrl}?username=${login.username}&password=${login.password}`;
     return this.http.get<any>(url, httpOptions);
   }
}

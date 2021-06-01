import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Login } from 'src/app/classes/login/login';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private loginUrl: string = 'http://localhost:8080/api/user/login';

  constructor(private http: HttpClient) {}

   public login(login: Login){
     var url = this.loginUrl;

     return this.http.post<any>(url, login.getParams(),
     {observe: 'response' as 'body'})
     .pipe(jwt => {return jwt;});
   }
}

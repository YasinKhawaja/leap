import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Login } from 'src/app/classes/login/login';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private loginUrl: string = 'http://localhost:8080/api/user/login';
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient) { 
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
   }

   public login(login: Login){
     //add login url
     let url = this.loginUrl;

     this.http.post(url, login.getParams(),
     { headers: this.contentHeaders})
     //remove the console log after tested
     .subscribe(data => {console.log(data)},
     error => {console.log(error)});
   }

   getUserToken(): Observable<string> {
    let url = this.loginUrl;
    console.log(url);

    return this.http.get<string>(url);
  }
}

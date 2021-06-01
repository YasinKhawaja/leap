import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from "../../classes/user/user"
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({providedIn: "root"})
export class UserService {
  private useradminsUrl: string = 'http://localhost:8080/api/useradmin';
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient, private router: Router) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
   }


   public register(user: User) {
     //gets token from the url
    let token = new URL(window.location.href).searchParams.get("token");

    //check if token is correct param
    console.log(token);

    //calls the api with the token to verify
    let url = this.useradminsUrl + '?token=' + token;

    //check if url is correct
    console.log(url);

    //post request
    this.http.post(url, user.getParams(),
      { headers: this.contentHeaders})
      .subscribe(
        data => {
          console.log(data)
          this.router.navigate(['login'])
        },
      error => {console.log(error)});
   }
}

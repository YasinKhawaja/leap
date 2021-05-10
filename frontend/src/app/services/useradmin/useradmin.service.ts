import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Useradmin } from "../../classes/useradmin/useradmin"
import { Observable } from 'rxjs';

@Injectable({providedIn: "root"})
export class UseradminService {
  private useradminsUrl: string = 'http://localhost:8080/api/useradmin';
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
   }


   public register(useradmin: Useradmin) {
     //gets token from the url
    let token = new URL(window.location.href).searchParams.get("token");

    //check if token is correct param
    console.log(token);

    //calls the api with the token to verify
    let url = this.useradminsUrl + '/register?token=' + token;

    //check if url is correct
    console.log(url);

    //post request
    this.http.post(url, useradmin.getParams(),
      { headers: this.contentHeaders})
      .subscribe(data => {console.log(data)},
      error => {console.log(error)});
   }
}

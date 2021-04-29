import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Useradmin } from "../../classes/useradmin/useradmin"
import { Observable } from 'rxjs';

@Injectable({providedIn: "root"})
export class UseradminService {

  private useradminsUrl: string = 'http://localhost:8080/useradmin';
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
   }

   //finds all companies and returns them in an array
   public findAll(): Observable<Useradmin[]> {
     //get request
     return this.http.get<Useradmin[]>(this.useradminsUrl);
   }


   public register(useradmin: Useradmin) {
    let url = this.useradminsUrl + '/register';
    //post request
    this.http.post(url, useradmin.getParams(),
      { headers: this.contentHeaders})
      .subscribe(data => {console.log(data)},
      error => {console.log(error)});
   }
}

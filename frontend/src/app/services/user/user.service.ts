import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from "../../classes/user/user"
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Injectable({providedIn: "root"})
export class UserService {
  private useradminsUrl: string = 'http://localhost:8080/api/useradmin';
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient, private router: Router) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
   }


   public register(user: User) {

    let token = new URL(window.location.href).searchParams.get("token");
    let url = this.useradminsUrl + '?token=' + token;

    this.http.post(url, user.getParams(),
      { headers: this.contentHeaders})
      .subscribe(
        () => {
          this.router.navigate(['login'])
          Swal.fire('Registered', 'You have succesfully registered', 'success')
        },
      error => {
        Swal.fire('Error', error.error.message, 'error')
      });
   }
}

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class ResetpasswordService {

  private resetpasswordURL: string = 'http://localhost:8080/api/user/resetpassword/';
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient, private router: Router) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
   }

     public resetPasswordMail(email: string) {

      var param = new URLSearchParams();
      param.set("email", email);

      this.http.post(this.resetpasswordURL, param.toString(),
        { headers: this.contentHeaders})
        .subscribe(
          () => {
            Swal.fire('Request submitted', `An email will be sent within the next hour, please check your spam folder.`, 'success');
            this.router.navigate(['login'])
          },
        error => {
          Swal.fire('Error', error.error.message, 'error')
        });
     }

     public resetPassword(password: string) {
      var token = new URL(window.location.href).searchParams.get("id");

      var param = new URLSearchParams();
      param.set("password", password);

      this.http.put(this.resetpasswordURL + token, param.toString(),
      {headers: this.contentHeaders})
      .subscribe(
        () => {
          Swal.fire('Password reset', 'Your password has succesfully been reset!', 'success')
        },
        error => {
          Swal.fire('Error', error.error.message, 'error')
        });
     }
}
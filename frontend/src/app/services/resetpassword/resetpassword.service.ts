import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class ResetpasswordService {

  private resetpasswordURL: string = 'http://localhost:8080/api/user/resetpassword';
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
   }

     public resetPassword(email: string) {

      var param = new URLSearchParams();
      param.set("email", email);

      this.http.post(this.resetpasswordURL, param.toString(),
        { headers: this.contentHeaders})
        .subscribe(
          () => {
            Swal.fire('Request submitted', `An email will be sent within the next hour, please check your spam folder.`, 'success');
          },
        error => {
          Swal.fire('Error', error.error.message, 'error')
        });
     }
}

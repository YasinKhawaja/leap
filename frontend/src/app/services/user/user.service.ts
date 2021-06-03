import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from "../../classes/user/user"
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Observable } from 'rxjs';

@Injectable({providedIn: "root"})
export class UserService {
  private userURL: string = 'http://localhost:8080/api/';
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient, private router: Router) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
   }


   public register(user: User) {

    let token = new URL(window.location.href).searchParams.get("token");
    let url = this.userURL + 'useradmin?token=' + token;

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

   public getUsers(companyId: string): Observable<User[]> {
     let url = `${this.userURL}user?companyId=${companyId}`;

     return this.http.get<User[]>(url);
   }

   public getUser(userId: string): Observable<User>{
     let url = `${this.userURL}user/${userId}`;

     return this.http.get<User>(url);
   }

   public delUser(userId: string){
     var url = `${this.userURL}user?userid=${userId}`;
     
     this.http.delete(url).subscribe(
       () => {
         this.router.navigate(['environments'])
         Swal.fire('Succes', 'User has been deleted', 'success')
       },
       error => {
         Swal.fire('error', error.error.message, 'error')
       }
     )
   }

   public updateUser(userId: string, user: User, role: string){
     var url = `${this.userURL}user?userid=${userId}&role=${role}`;

     this.http.put<User>(url, user.getParams(),
     {headers: this.contentHeaders})
     .subscribe(
       () => {
         this.router.navigate(['environments'])
         Swal.fire('Success', 'User has been updated', 'success')
       },
       error => {
         Swal.fire('Error', error.error.message, 'error')
       }
     )
   }
   
   public addUser(companyId: string, user: User, role: string){
    var url = `${this.userURL}user?companyId=${companyId}&role=${role}`

    this.http.post<User>(url, user.getParams(),
    {headers: this.contentHeaders})
    .subscribe(
      () => {
        this.router.navigate(['environments'])
        Swal.fire('Success', 'User has been created, an email will be sent to this user', 'success') 
      },
      error => {
        Swal.fire('Error', error.error.message, 'error')
      }
    )
   }
}

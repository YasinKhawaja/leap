import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from "../../classes/user/user"
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Observable } from 'rxjs';

@Injectable({ providedIn: "root" })
export class UserService {
  private userURL: string = '//localhost:8080/api/';

  constructor(private http: HttpClient, private router: Router) {
  }


  public register(user: User) {

    let token = new URL(window.location.href).searchParams.get("token");
    let url = this.userURL + 'useradmin?token=' + token;

    this.http.post(url, user)
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

  public getUser(userId: string): Observable<User> {
    let url = `${this.userURL}user/${userId}`;

    return this.http.get<User>(url);
  }

  public delUser(userId: string) {
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

  public updateUser(userId: string, user: User, role: string) {
    var url = `${this.userURL}user`;

    this.http.put<User>(url, user, {
      params: {
        userid: userId,
        role: role
      }
    }).subscribe(
      () => {
        this.router.navigate(['environments'])
        Swal.fire('Success', 'User has been updated', 'success')
      },
      error => {
        Swal.fire('Error', 'Failed to update user, new email or username might be in use', 'error')
      }
    )
  }

  public addUser(companyId: string, user: User, role: string) {
    var url = `${this.userURL}user`

    this.http.post<User>(url, user, {
      params: {
        company: companyId,
        role: role
      }
    }).subscribe(
      () => {
        Swal.fire('Success', 'User has been created, an email will be sent to this user', 'success')
      },
      () => {
        Swal.fire('Error', 'Failed to create user, email or username might be in use.', 'error')
      }
    )
  }
}

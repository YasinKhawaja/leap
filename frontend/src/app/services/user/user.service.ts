import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from "../../classes/user/user"
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Observable } from 'rxjs';
import { JwtService } from '../jwt/jwt.service';

@Injectable({ providedIn: "root" })
export class UserService {
  private userURL: string = '//localhost:8080/api/';

  constructor(private http: HttpClient, private router: Router, private jwt: JwtService) {
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
        (error: HttpErrorResponse) => {
          Swal.fire('Error', error.error, 'error')
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
      },
      () => {
        Swal.fire('error', `Failed to delete this user.`, 'error')
      }
    )
  }

  public updateUser(userId: string, user: User, role: string, sameuser: boolean) {
    var url = `${this.userURL}user`;

    this.http.put<User>(url, user, {
      params: {
        userid: userId,
        role: role
      }
    }).subscribe(
      async () => {
        if (sameuser) {
          await this.jwt.getNewJwt(user.username)
          this.jwt.setRole()
        }
        this.router.navigate(['environments'])
      },
      (error: HttpErrorResponse) => {
        Swal.fire('Error', error.error, 'error')
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
        this.router.navigate(['environments'])
        Swal.fire('Success', 'User has been created, an email will be sent to this user', 'success')
      },
      (error: HttpErrorResponse) => {
        Swal.fire('Error', error.error, 'error')
      })
  }
}

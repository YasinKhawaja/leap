import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { BehaviorSubject, interval, observable, Observable, Subject, Subscription } from 'rxjs';
import Swal from 'sweetalert2';
import { NavbarService } from '../navbar/navbar.service';

@Injectable({
  providedIn: 'root'
})
export class JwtService {
  private jwtUrl: string = 'http://localhost:8080/api/user/jwt';
  userstatus: BehaviorSubject<boolean> = new BehaviorSubject(false);
  private contentHeaders: HttpHeaders;
  private userIdleCheck = new Subject<boolean>();

  constructor(private ns: NavbarService, private http: HttpClient, private router: Router) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')

    if (this.validateJWT()) {
      this.userIdleCheck.next(true);
    } else {
      this.userIdleCheck.next(false)
    }
  }

  tokenRefresh() {
    setInterval(() => {
      if(this.getUserBoolean().getValue()){
        this.getNewJwt()
      }
    }, 800000);
  }

  storeJWT(token: string) {
    this.ns.createCookie("jwt", token, 1);
  }

  checkJWT() {
    var token = this.ns.readCookie("jwt")
    var helper = new JwtHelperService();

    var jwtBody = helper.decodeToken(token);
    var role = JSON.stringify(jwtBody.role);
    role = role.substring(2, role.length - 2).toLowerCase();

    var jwtIsExp = helper.isTokenExpired(token);

    if (jwtIsExp) {
      this.logout()
      Swal.fire('Error', 'Your session has expired', 'error')
    } else {
      return role;
    }

    return null;
  }

  getNewJwt() {
    console.log("getting new JWT automatically")
    var token = this.ns.readCookie("jwt")
    var url = this.jwtUrl;

    var param = new URLSearchParams();
    param.set('token', token);

    return this.http.post<any>(url, param.toString(),
      {
        headers: this.contentHeaders,
        observe: 'response' as 'body'
      })
      .pipe(jwt => {
        return jwt;
      })
      .subscribe(
        (data: HttpResponse<any>) => {
          var newToken = data.headers.get("authorization").replace('Bearer ', '');
          this.storeJWT(newToken);
        },
        () => {
          this.logout();
          Swal.fire('Error', 'Your session has expired', 'error')
          return null;
        }
      );
  }

  setUserIdle(userIdle: boolean) {
    this.userIdleCheck.next(userIdle);
  }

  getUserIdle(): Observable<boolean> {
    return this.userIdleCheck.asObservable();
  }

  getUserStatus(): boolean {
    if (this.userstatus.getValue().toString() == "true" || this.validateJWT()) {
      return true;
    }
    else {
      return false;
    }
  }

  getUserBoolean(): BehaviorSubject<boolean> {
    if (this.validateJWT()) {
      this.userstatus.next(true);
    }
    return this.userstatus;
  }

  validateJWT(): boolean {
    var token = this.ns.readCookie("jwt")
    if (token == "") {
      return false;
    }
    var helper = new JwtHelperService();

    var jwtIsExp = helper.isTokenExpired(token);

    if (jwtIsExp) {
      this.logout()
      Swal.fire('Error', 'Your session has expired', 'error')
    } else {
      return true;
    }
    return false;
  }

  getUsername(): string {
    var cookie = this.ns.readCookie("jwt");
    if (cookie != "") {
      var helper = new JwtHelperService();
      var jwtBody = helper.decodeToken(cookie);
      return jwtBody.sub;
    }
    return null;
  }

  loggedin(username: string) {
    var cookie = this.ns.readCookie("jwt")
    var helper = new JwtHelperService();

    var jwtBody = helper.decodeToken(cookie);
    var jwtIsExp = helper.isTokenExpired(cookie);

    var jwtUsername = jwtBody.sub;

    if (jwtUsername == username && !jwtIsExp) {
      this.userstatus.next(true);
      this.router.navigate(['/environments'])
    }
    else if (jwtIsExp) {
      this.logout()
      Swal.fire('Error', 'Your session has expired', 'error')
    }
  }

  logout() {
    this.ns.createCookie("jwt", "", 0);
    this.ns.createCookie("Capability", "", 0);
    this.ns.environmentDeselect();
    this.userstatus.next(false);
    this.setUserIdle(false);
    this.router.navigate(['login'])
  }
}

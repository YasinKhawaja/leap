import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import Swal from 'sweetalert2';
import { NavbarService } from '../navbar/navbar.service';

@Injectable({
  providedIn: 'root'
})
export class JwtService {
  private jwtUrl: string = '//localhost:8080/api/user/jwt';
  userstatus: BehaviorSubject<boolean> = new BehaviorSubject(false);
  private contentHeaders: HttpHeaders;
  private userIdleCheck = new Subject<boolean>();
  interval;

  constructor(private ns: NavbarService, private http: HttpClient, private router: Router) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')

    if (this.validateJWT()) {
      this.userIdleCheck.next(true);
    } else {
      this.userIdleCheck.next(false)
    }
  }

  tokenRefresh() {
    this.interval = setInterval(() => {
      this.getNewJwt()
    }, 600000);
  }

  storeJWT(token: string) {
    this.ns.createCookie("jwt", token, 1);
  }

  checkCompany() {
    var token = this.ns.readCookie("jwt")
    var helper = new JwtHelperService();

    var jwtBody = helper.decodeToken(token);
    var company = JSON.stringify(jwtBody.company);
    company = company.substring(1, company.length - 1).toLowerCase();

    var jwtIsExp = helper.isTokenExpired(token);
    if (jwtIsExp) {
      this.logout()
      Swal.fire('Warning', 'Your session has expired, please log in again', 'warning')
    } else {
      return company;
    }
    return null;
  }

  checkRole() {
    var token = this.ns.readCookie("jwt")
    var helper = new JwtHelperService();

    var jwtBody = helper.decodeToken(token);
    var role = JSON.stringify(jwtBody.role);
    role = role.substring(2, role.length - 2).toLowerCase();

    var jwtIsExp = helper.isTokenExpired(token);

    if (jwtIsExp) {
      this.logout()
      Swal.fire('Warning', 'Your session has expired, please log in again', 'warning')
    } else {
      return role;
    }

    return null;
  }

  getNewJwt() {
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
        () => {
        },
        () => {
          this.logout();
          Swal.fire('Warning', 'Your session has expired, please log in again', 'warning')
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
    if (token == '' || token == null) {
      return false;
    }
    var helper = new JwtHelperService();

    var jwtIsExp = helper.isTokenExpired(token);

    if (jwtIsExp) {
      this.logout()
      Swal.fire('Warning', 'Your session has expired, please log in again', 'warning')
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
      Swal.fire('Warning', 'Your session has expired, please log in again', 'warning')
    }
  }

  logout() {
    this.ns.eraseCookie("jwt");
    this.ns.eraseCookie("Capability");
    this.ns.eraseCookie("Environment");
    this.ns.eraseCookie("EnvironmentName");
    this.ns.eraseCookie("Program");
    this.ns.environmentDeselect();
    this.userstatus.next(false);
    this.setUserIdle(false);
    clearInterval(this.interval);
    this.router.navigate(['login'])
  }
}

import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { BehaviorSubject } from 'rxjs';
import { NavbarService } from '../navbar/navbar.service';

@Injectable({
  providedIn: 'root'
})
export class JwtService {
  private jwtUrl: string = 'http://localhost:8080/api/user/jwt';
  userstatus: BehaviorSubject<boolean> = new BehaviorSubject(false);

  constructor(private ns: NavbarService, private http:HttpClient, private router:Router) { }

  storeJWT(token: string){
    this.ns.createCookie("jwt", token, 1);
  }

  checkJWT(){
    var token = this.ns.readCookie("jwt")
    var helper = new JwtHelperService();

    var jwtBody = helper.decodeToken(token);
    var role = JSON.stringify(jwtBody.role);
    role = role.substring(2, role.length - 2).toLowerCase();

    var jwtIsExp = helper.isTokenExpired(token);

    if(jwtIsExp){
      this.getNewJwt()
    } else {
      return role;
    }

    return null;
  }

  getNewJwt(){
    var token = this.ns.readCookie("jwt")
    var url = this.jwtUrl;

    return this.http.post<any>(url, token,
      {observe: 'response' as 'body'})
      .pipe(jwt => {
        return jwt;
      })
      .subscribe(
        (data: HttpResponse<any>) => {
          var newToken = data.headers.get("authorization").replace('Bearer ', '');
          this.storeJWT(newToken);
        },
        error => {
          console.log(error);
          this.logout();
        }
      );
  }

  getUserStatus(): boolean{
    if(this.userstatus.getValue().toString() == "true" || this.validateJWT()){
      console.log("logged in");
      return true;
    }
    else {
      return false;
    }
  }

  validateJWT(): boolean{
    var token = this.ns.readCookie("jwt")
    if(token == ""){
      return null
    }
    var helper = new JwtHelperService();

    var jwtIsExp = helper.isTokenExpired(token);

    if(jwtIsExp){
      this.getNewJwt()
    } else {
      return true;
    }
    return null;
  }
  
  loggedin(username: string){
    var cookie = this.ns.readCookie("jwt")
    var helper = new JwtHelperService();

    var jwtBody = helper.decodeToken(cookie);
    var jwtIsExp = helper.isTokenExpired(cookie);

    var jwtUsername = jwtBody.sub;

    if(jwtUsername == username && !jwtIsExp){
      this.userstatus.next(true);
      this.router.navigate(['/environments'])
    }
    else if(jwtIsExp){
      this.getNewJwt()
    }
  }

  logout(){
    this.ns.createCookie("jwt", "", 0);
    this.userstatus.next(false);
    this.router.navigate(['login'])
  }
}

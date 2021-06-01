import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { interval, Subscription } from 'rxjs';
import { JwtService } from './services/jwt/jwt.service';
import { NavbarService } from './services/navbar/navbar.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  private sub: Subscription;
  title: string;
  environmentName: string
  username: string
  environmentId: string;

  constructor(public ns: NavbarService, public jwt: JwtService, public router: Router) {
    this.title = 'LEAP-webapp'
    this.environmentName = "Environments";
  }
  ngOnInit(): void {
    const source = interval(800000);
    this.sub = source.subscribe(
      (countdown) => {
        console.log(countdown);
        if(this.jwt.getUserStatus){
          this.jwt.getNewJwt()
       }
      });
  }

  deselect(): void{
    this.ns.environmentDeselect()
   
  }

  getEnvironmentId(){
    this.environmentId = this.ns.getEnvironment();
  }

  logout(){
    this.jwt.logout();
  }

  getEnvironmentname(): string{
    this.environmentName = this.ns.getEnvironmentName();
    return this.environmentName;
  }

  getUsername(): string{
    if (this.jwt.getUsername() != null){
      this.username = this.jwt.getUsername();
    }
    else {
      this.username = "User";
    }
    return this.username;
  }

  getRouter(): boolean{
    if(this.router.isActive("home", true) || this.router.isActive("/", true)){
      return true;
    } else {
      return false;
    }
  }
}

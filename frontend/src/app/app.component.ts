import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JwtService } from './services/jwt/jwt.service';
import { NavbarService } from './services/navbar/navbar.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{
  title: string;
  environmentName: string
  username: string
  environmentId: string;

  constructor(public ns: NavbarService, public jwt: JwtService, public router: Router) {
    this.title = 'LEAP-webapp'
    this.environmentName = "Environments";
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
    if(this.router.isActive("home", true)){
      return true;
    } else {
      return false;
    }
  }
}

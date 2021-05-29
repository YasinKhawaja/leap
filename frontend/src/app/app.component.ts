import { Component } from '@angular/core';
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

  constructor(public ns: NavbarService, public jwt: JwtService) {
    this.title = 'LEAP-webapp'
    this.environmentName = this.getEnvironmentname();
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
    console.log("environment changed");
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
}

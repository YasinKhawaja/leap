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

  environmentId: string;

  constructor(public ns: NavbarService, public jwt: JwtService) {
    this.title = 'LEAP-webapp'
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
}

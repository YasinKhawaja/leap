import { Component } from '@angular/core';
import { NavbarService } from './services/navbar/navbar.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string;
  

  constructor(public ns: NavbarService) {
    this.title = 'LEAP-webapp'
  }

  deselect(): void{
    this.ns.environmentDeselect()
   
  }

  //tijdelijk
  logout(): void {
    this.ns.environmentDeselect()
    this.ns.userLogout()
  }

}

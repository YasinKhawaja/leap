import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user/user.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-user-delete',
  templateUrl: './user-delete.component.html',
  styleUrls: ['./user-delete.component.css']
})
export class UserDeleteComponent  {

  constructor(private us: UserService, private router: Router, private location: Location) { }

  deleteUser() {
    let userId = this.router.url.split('/')[2];

    this.us.delUser(userId);
  }
  
  navigateBack(){
    this.location.back();
  }
}

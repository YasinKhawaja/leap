import { Component, OnInit } from '@angular/core';
import { Environment } from 'src/app/classes/environment/environment';
import { User } from 'src/app/classes/user/user';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { UserService } from 'src/app/services/user/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-environment',
  templateUrl: './environment.component.html',
  styleUrls: ['./environment.component.css']
})
export class EnvironmentComponent implements OnInit {

  role : string;
  environments: Environment[];
  users: User[];

  constructor(private es: EnvironmentService, private ns: NavbarService, public jwt: JwtService, private us: UserService) {
    this.environments = [];
    this.users = [];
  }

  ngOnInit(): void {
    this.getAllEnvironments();
    this.role = this.jwt.checkRole();
    if(this.role == "admin"){
      this.getUsers();
    }
  }

  getAllEnvironments(): void {
    this.es.getAllEnvironments()
      .subscribe(
        res => { this.environments = res; console.log(res); },
        err => console.error(err)
      );
  }

  getUsers(): void{
    this.us.getUsers(this.jwt.checkCompany())
    .subscribe(
      result => {
        this.users = result
      },
      error => {
        Swal.fire('Error', error.error.message, 'error');
      }
    )
  }

  environmentId(environmentId, environmentName): void {
    this.ns.setEnvironment(environmentId);
    this.ns.setEnvironmentName(environmentName);
  }
}

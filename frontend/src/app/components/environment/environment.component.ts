import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/classes/company/company';
import { Environment } from 'src/app/classes/environment/environment';
import { User } from 'src/app/classes/user/user';
import { CompanyService } from 'src/app/services/company/company.service';
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
  companies: Company[];
  companyid: string;

  constructor(private es: EnvironmentService, private ns: NavbarService, public jwt: JwtService, private us: UserService, private cs: CompanyService) {
    this.environments = [];
    this.users = [];
    this.companies = [];
  }

  ngOnInit(): void {
    this.role = this.jwt.checkRole();

    if(this.role == "application admin"){
      this.getCompanies();
    } else {
      this.companyid = this.jwt.checkCompany();
      this.getAllEnvironments();
    }
    if(this.role == "admin"){
      this.getUsers();
    }
  }

  getAllEnvironments(): void {
    this.es.getAllEnvironments(this.companyid)
      .subscribe(
        res => { this.environments = res; },
        err => console.error(err)
      );
  }

  getCompanies(): void{
    this.cs.getAllCompanies(this.role)
      .subscribe(
        result => {
          this.companies = result;
        },
        error => {
          Swal.fire('Error', error.error.message, 'error');
        }
      )
  }

  getUsers(): void{
    this.us.getUsers(this.companyid)
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

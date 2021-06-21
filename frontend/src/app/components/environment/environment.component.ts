import { HttpErrorResponse } from '@angular/common/http';
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

  role: string;
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
    this.getRole()
  }

  getAllEnvironments(): void {
    this.es.getAllEnvironments(this.companyid)
      .subscribe(
        res => {
          this.environments = res;
        },
        (error: HttpErrorResponse) => {
          Swal.fire('Error', error.error, 'error')
        });
  }

  getCompanies(): void {
    this.cs.getAllCompanies()
      .subscribe(
        result => {
          this.companies = result;
        },
        (error: HttpErrorResponse) => {
          Swal.fire('Error', error.error, 'error')
        });
  }

  getUsers(): void {
    this.us.getUsers(this.companyid)
      .subscribe(
        result => {
          this.users = result
        },
        (error: HttpErrorResponse) => {
          Swal.fire('Error', error.error, 'error')
        });
  }

  getRole() {
    this.jwt.setRole()
    this.role = this.jwt.getRole()
    if (this.role == "application admin") {
      this.getCompanies();
    } else {
      this.companyid = this.jwt.checkCompany();
      this.getAllEnvironments();
    }
    if (this.role == "admin") {
      this.getUsers();
    }
  }

  environmentId(environmentId, environmentName): void {
    this.ns.setEnvironmentCookie(environmentId);
    this.ns.setEnvironmentName(environmentName);
  }

  approve(name: string, approved: any, companyid: any) {
    var status: boolean = true;
    if (approved) {
      status = false;
    }
    if (confirm(`Are you sure you want to ${status ? "accept" : "lock"} ${name}?`)) {
      this.cs.changeCompanyStatus(status.toString(), companyid)
        .then(
          (data) => {
            this.companies = data
          }
        )
    }
  }
}

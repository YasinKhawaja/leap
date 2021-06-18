import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/classes/company/company';
import { CompanyService } from 'src/app/services/company/company.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { JwtService } from 'src/app/services/jwt/jwt.service';

@Component({
  selector: 'app-company-request',
  templateUrl: './company-request.component.html',
  styleUrls: ['./company-request.component.css']
})
export class CompanyRequestComponent implements OnInit {

  company!: Company;
  role: string;
  companyid: string;

  constructor(private c: CompanyService, private router: Router, private jwt: JwtService) {
  }

  accept(): void {
    this.c.accept(true, this.companyid);
  }

  decline(): void {
    this.c.accept(false, this.companyid);
  }

  ngOnInit(): void {
    this.role = this.jwt.checkRole();
    this.companyid = this.router.url.split("/")[3]
    this.c.getCompany(this.companyid).subscribe(data => {
      this.company = data;
    },
      error => {
        Swal.fire("Error", `Failed to load company id ${this.companyid}`, "error");
        this.router.navigate(['environments'])
      })
  }

}

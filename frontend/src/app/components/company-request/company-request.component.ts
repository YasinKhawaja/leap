import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/classes/company/company';
import { CompanyService } from 'src/app/services/company/company.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-company-request',
  templateUrl: './company-request.component.html',
  styleUrls: ['./company-request.component.css']
})
export class CompanyRequestComponent implements OnInit {

  company!: Company;

  constructor(private c: CompanyService,  private router: Router) { 
   }

   accept(): void {
    this.c.accept(true);
    this.router.navigate(['/'])
   }

   decline(): void{
    this.c.accept(false);
    this.router.navigate(['/'])
   }

  ngOnInit(): void {
    this.c.getCompany().subscribe(data => { 
      this.company = data;
      console.log(data);
    },
    error => {
      console.error(error)
    })
  }

}

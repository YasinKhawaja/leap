import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/classes/company/company';
import { CompanyService } from 'src/app/services/company/company.service';

@Component({
  selector: 'app-company-request',
  templateUrl: './company-request.component.html',
  styleUrls: ['./company-request.component.css']
})
export class CompanyRequestComponent implements OnInit {

  company!: Company;

  constructor(private c: CompanyService) { 
   }

   accept(): void {
    this.c.accept(true);
   }

   decline(): void{
    this.c.accept(false);
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

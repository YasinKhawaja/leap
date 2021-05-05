import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Company } from '../../classes/company/company';
import { Observable } from 'rxjs';

@Injectable({providedIn: "root"})
export class CompanyService {

  private companiesUrl: string = 'http://localhost:8080/companies';
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
   }

   //finds all companies and returns them in an array
   public findAll(): Observable<Company[]> {
     //get request
     return this.http.get<Company[]>(this.companiesUrl);
   }

   //add a company
   public register(company: Company) {
    let url = this.companiesUrl + '/register';
    //post request
    this.http.post(url, company.getParams(),
      { headers: this.contentHeaders})
      .subscribe(data => {console.log(data)},
      error => {console.log(error)});
   }
}

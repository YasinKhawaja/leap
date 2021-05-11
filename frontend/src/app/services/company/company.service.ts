import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Company } from '../../classes/company/company';
import { Observable } from 'rxjs';

@Injectable({providedIn: "root"})
export class CompanyService {

  private companiesUrl: string = 'http://localhost:8080/api/companies';
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
   }

   //get 
   public getCompany(): Observable<Company> {
     let token = new URL(window.location.href).searchParams.get("token");
     let id = new URL(window.location.href).searchParams.get("id");
     let url = this.companiesUrl + "/register/" + id + "/?token=" + token;
     //testing url
     console.log(url);

     return this.http.get<Company>(url)
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

   //change application status of a company
   public accept(accepted: boolean) {
    let id = new URL(window.location.href).searchParams.get("id");
    let url = this.companiesUrl + "/register/" + id + "/applicationStatus";

    let params = new URLSearchParams();
    params.set("accepted", accepted.toString());

    this.http.post(url, params.toString(),
      { headers: this.contentHeaders})
      .subscribe(data => {console.log(data)},
      error => {console.log(error)});
   }
}

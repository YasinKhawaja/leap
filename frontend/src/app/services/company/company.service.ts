import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Company } from '../../classes/company/company';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';

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

     return this.http.get<Company>(url)
   }


   public register(company: Company) {
    let url = this.companiesUrl + '/register';

    this.http.post(url, company.getParams(),
      { headers: this.contentHeaders})
      .subscribe(
        () => {
          Swal.fire('Registered', 'You have succesfully registered your company', 'success')
        },
      error => {
        Swal.fire('Error', error.error.message, 'error')
      });
   }


   public accept(accepted: boolean) {
    let id = new URL(window.location.href).searchParams.get("id");
    let url = this.companiesUrl + "/register/" + id + "/applicationStatus";

    let params = new URLSearchParams();
    params.set("accepted", accepted.toString());

    this.http.post(url, params.toString(),
      { headers: this.contentHeaders})
      .subscribe(
        () => {
          //change true/false to apporved/denied
          Swal.fire('Accepted', 'You have approved/denied the application: ' + accepted, 'success')
        },
      error => {
        Swal.fire('Error', error.error.message, 'error')
      });
   }
}

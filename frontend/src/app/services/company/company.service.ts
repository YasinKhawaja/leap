import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Company } from '../../classes/company/company';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Injectable({providedIn: "root"})
export class CompanyService {

  private companiesUrl: string = '//localhost:8080/api/companies';
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient, private router: Router) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
   }

   public getCompany(role: string): Observable<Company> {
     let token = new URL(window.location.href).searchParams.get("token");
     let url = `${this.companiesUrl}/${token}?role=${role}`;

     return this.http.get<Company>(url)
   }

   public getAllCompanies(role: string): Observable<Company[]> {
     var url = `${this.companiesUrl}?role=${role}`;
     return this.http.get<Company[]>(url);
   } 


   public register(company: Company) {
    let url = this.companiesUrl;

    this.http.post(url, company.getParams(),
      { headers: this.contentHeaders})
      .subscribe(
        () => {
          Swal.fire('Registered', 'You have succesfully registered your company', 'success')
          this.router.navigate(['login'])
        },
      error => {
        Swal.fire('Error', error.error.message, 'error')
      });
   }


   public accept(accepted: boolean, role: string) {
    let token = new URL(window.location.href).searchParams.get("token");
    let url = `${this.companiesUrl}/${token}/applicationStatus?role=${role}`;

    let params = new URLSearchParams();
    params.set("accepted", accepted.toString());

    this.http.post(url, params.toString(),
      { headers: this.contentHeaders})
      .subscribe(
        () => {
          Swal.fire('Accepted', `You have ${accepted ? " approved ":" denied "} the application.`, 'success')
          this.router.navigate(['/'])
        },
      error => {
        Swal.fire('Error', error.error.message, 'error')
        this.router.navigate(['/'])
      });
   }
}

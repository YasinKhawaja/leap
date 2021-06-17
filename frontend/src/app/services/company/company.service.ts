import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Company } from '../../classes/company/company';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Injectable({ providedIn: "root" })
export class CompanyService {

  private companiesUrl: string = '//localhost:8080/api/companies';
  private companyURI: string = '//localhost:8080/api/company'
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient, private router: Router) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
  }

  public getCompany(companyid: string): Observable<Company> {
    let url = `${this.companyURI}/application/${companyid}`;

    return this.http.get<Company>(url)
  }

  public getAllCompanies(): Observable<Company[]> {
    var url = `${this.companiesUrl}`;
    return this.http.get<Company[]>(url);
  }


  public register(company: Company) {
    let url = this.companyURI;

    this.http.post(url, company)
      .subscribe(
        () => {
          Swal.fire('Registered', 'You have registered your company, please wait for one of the Admins to review your application. You will be sent an email with the result.', 'success')
          this.router.navigate(['login'])
        },
        error => {
          Swal.fire('Error', 'Failed to register your company', 'error')
        });
  }


  public accept(accepted: boolean, companyid: string) {
    let url = `${this.companyURI}/application/${companyid}`;

    let params = new URLSearchParams();
    params.set("accepted", accepted.toString());

    this.http.post(url, params.toString(),
      { headers: this.contentHeaders })
      .subscribe(
        () => {
          Swal.fire('Accepted', `You have ${accepted ? " approved " : " denied "} the application.`, 'success')
          this.router.navigate(['environments'])
        },
        () => {
          Swal.fire('Error', `Failed to ${accepted ? " approve " : " deny "} the application.`, 'error')
        });
  }

  public async changeCompanyStatus(status: string, companyid: string): Promise<Company[]> {
    let url = `${this.companyURI}/status`

    let companies: Company[] = []

    var params = new URLSearchParams()
    params.set("status", status)
    params.set("companyid", companyid)

    await this.http.post(url, params.toString(),
      { headers: this.contentHeaders })
      .toPromise()
      .then(
        () => { },
        () => {
          Swal.fire('Error', `Failed to change company approval to ${status}`, 'error')
        }
      )
    await this.getAllCompanies()
      .toPromise()
      .then(
        (data) => {
          companies = data
          return companies
        },
        () => {
          Swal.fire('Error', `Failed to reload companies`, 'error')
        })
    return companies
  }
}

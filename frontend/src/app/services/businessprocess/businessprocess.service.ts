import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';
import { Businessprocess } from '../../classes/businessprocess/businessprocess';

@Injectable({
  providedIn: 'root'
})
export class BusinessprocessService {

  private businessprocessURL: string = 'http://localhost:8080/api/businessprocess'
  private contentheaders: HttpHeaders

  constructor(private http: HttpClient, private router: Router) {
    this.contentheaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
  }

  public getBusinessProcesses(environmentid: string): Observable<Businessprocess[]> {
    var url = `${this.businessprocessURL}es/${environmentid}`;

    return this.http.get<Businessprocess[]>(url);
  }

  public getBusinessProcess(businessprocessid: string): Observable<Businessprocess>{
    var url = `${this.businessprocessURL}/${businessprocessid}`;

    return this.http.get<Businessprocess>(url);
  }

  public addBusinessProcess(environmentid: string, businessprocess: Businessprocess){
    var url = `${this.businessprocessURL}/${environmentid}`;

    return this.http.post<Businessprocess>(url, businessprocess.getParams(),
    {headers: this.contentheaders})
      .subscribe(
        () => {
          this.router.navigate(['businessprocess'])
          Swal.fire('Success', 'You have succesfully created a Business Process', 'success')
        },
        error => {
          Swal.fire('Error', error.error.message, 'error')
        }
      )
  }

  public updateBusinessProcess(businessprocessid: string, businessprocess: Businessprocess){
    var url = `${this.businessprocessURL}/${businessprocessid}`;

    return this.http.put<Businessprocess>(url, businessprocess.getParams(),
    {headers: this.contentheaders})
    .subscribe (
      () => {
        this.router.navigate(['businessprocess'])
        Swal.fire('Success', 'You have successfully updated the Process', 'success')
      },
      error => {
        Swal.fire('Error', error.error.message, 'error')
      }
    )
  }

  public deleteBusinessProcess(businessprocessid: string){
    var url = `${this.businessprocessURL}/${businessprocessid}`;

    this.http.delete(url).subscribe(
      () => {
        this.router.navigate(['businessprocess'])
        Swal.fire('Success', 'You have successfully deleted the Process', 'success')
      },
      error => {
        Swal.fire('Error', error.error.message, 'error')
      }
    )
  }
}

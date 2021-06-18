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

  private businessprocessURL: string = '//localhost:8080/api/businessprocess'
  private contentheaders: HttpHeaders

  constructor(private http: HttpClient, private router: Router) {
    this.contentheaders = new HttpHeaders().set('Content-Type', 'application/json')
  }

  public getBusinessProcesses(environmentid: string): Observable<Businessprocess[]> {
    var url = `${this.businessprocessURL}es/${environmentid}`;

    return this.http.get<Businessprocess[]>(url);
  }

  public getBusinessProcess(businessprocessid: string): Observable<Businessprocess> {
    var url = `${this.businessprocessURL}/${businessprocessid}`;

    return this.http.get<Businessprocess>(url);
  }

  public addBusinessProcess(environmentid: string, businessprocess: Businessprocess) {
    var url = `${this.businessprocessURL}/${environmentid}`;

    return this.http.post<Businessprocess>(url, businessprocess,
      { headers: this.contentheaders })
      .subscribe(
        () => {
          //this.router.navigate(['businessprocess'])
          window.location.reload();
        },
        () => {
          Swal.fire('Error', `Failed to create the business process: ${businessprocess.name}`, 'error')
        }
      )
  }

  public updateBusinessProcess(businessprocessid: string, businessprocess: Businessprocess) {
    var url = `${this.businessprocessURL}/${businessprocessid}`;

    return this.http.put<Businessprocess>(url, businessprocess,
      { headers: this.contentheaders })
      .subscribe(
        () => {
          //this.router.navigate(['businessprocess'])
          window.location.reload();
        },
        () => {
          Swal.fire('Error', `Failed to update business process with id: ${businessprocess.id}`, 'error')
        }
      )
  }

  public deleteBusinessProcess(businessprocessid: string) {
    var url = `${this.businessprocessURL}/${businessprocessid}`;

    this.http.delete(url).subscribe(
      () => {
       // this.router.navigate(['businessprocess'])
       window.location.reload();
      },
      () => {
        Swal.fire('Error', 'Failed to create the business process', 'error')
      }
    )
  }
}

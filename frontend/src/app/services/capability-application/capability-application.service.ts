import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CapabilityApplication } from 'src/app/classes/capability-application/capability-application';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class CapabilityApplicationService {

  private capabilityApplicationURL: string = 'http://localhost:8080/api/capitapp';
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient, private router:Router) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
  }

  public getCapabilityApplications(capabilityId: string): Observable<CapabilityApplication[]> {
    let url = `${this.capabilityApplicationURL}/${capabilityId}`;
    return this.http.get<CapabilityApplication[]>(url);
  }

  public createCapabilityApplication(capabilityId: string, capabilityApplication: CapabilityApplication){
    let url = `${this.capabilityApplicationURL}/${capabilityId}`;
    return this.http.post<CapabilityApplication>(url, capabilityApplication.getParams(),
    {headers: this.contentHeaders})
    .subscribe(
      () => {
      Swal.fire('Success', 'You have succesfully linked the IT-Application', 'success')
      this.router.navigate([`capability-application/`])
    },
    error => {
      Swal.fire('Error', error.error.message, 'error')
    });
  }

  public updateCapabilityApplication(capabilityApplicationID: string, capabilityApplication: CapabilityApplication) {
    let url = `${this.capabilityApplicationURL}/${capabilityApplicationID}`

    this.http.put<CapabilityApplication>(url, capabilityApplication.getParams(),
    {headers: this.contentHeaders})
    .subscribe(
      () => {
      Swal.fire('Success', 'You have succesfully updated the Capability-Application link', 'success')
      this.router.navigate([`capability-application/`])
    },
    error => {
      Swal.fire('Error', error.error.message, 'error')
    });
  }

  public deleteCapabilityApplication(capabilityApplicationID: string) {
    let url = `${this.capabilityApplicationURL}/${capabilityApplicationID}`

    this.http.delete(url).subscribe(
      () => {
        Swal.fire('Success', 'You have succesfully deleted the Capability-Application link', 'success')
        this.router.navigate([`capability-application/`])
      });
  }
}

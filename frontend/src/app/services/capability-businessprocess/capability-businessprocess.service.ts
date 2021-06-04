import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CapabilityBusinessprocess } from 'src/app/classes/capability-businessprocess/capability-businessprocess';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class CapabilityBusinessprocessService {

  private cbpURL: string = 'http://localhost:8080/api/capabilitybusinessprocess' 
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient, private router: Router) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
  }

  public getCapabilityBusinessProcess(capabilityid: string): Observable<CapabilityBusinessprocess[]> {
    var url = `${this.cbpURL}/${capabilityid}`;
    return this.http.get<CapabilityBusinessprocess[]>(url);
  }

  public addCapabilityBusinessProcess(capabilityid: string, capabilitybusinessprocess: CapabilityBusinessprocess){
    var url = `${this.cbpURL}/${capabilityid}`;
    return this.http.post<CapabilityBusinessprocess>(url, capabilitybusinessprocess.getParams(),
    {headers: this.contentHeaders})
      .subscribe(
        () => {
          Swal.fire('Success', 'You have succesfully linked the Process', 'success')
          this.router.navigate(['capability-businessprocess'])
        },
        error => {
          Swal.fire('Error', error.error.message, 'error')
        }
      );
  }

  public deleteCapabilityBusienssProcess(capabilitybusinessprocessid: string){
    var url = `${this.cbpURL}/${capabilitybusinessprocessid}`;
    this.http.delete(url).subscribe(
      () => {
        Swal.fire('Success', 'You have succesfully deleted the Process', 'success')
        this.router.navigate(['capability-businessprocess'])
      }
    )
  }
}

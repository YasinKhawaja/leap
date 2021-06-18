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

  private cbpURL: string = '//localhost:8080/api/capabilitybusinessprocess' 
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
         // this.router.navigate(['capability-businessprocess'])
         window.location.reload();

        },
        () => {
          Swal.fire('Error', 'Failed to add capability-business process link', 'error')
        }
      );
  }

  public deleteCapabilityBusienssProcess(capabilitybusinessprocessid: string){
    var url = `${this.cbpURL}/${capabilitybusinessprocessid}`;
    this.http.delete(url).subscribe(
      () => {
       // this.router.navigate(['capability-businessprocess'])
       window.location.reload();
      }
    )
  }
}

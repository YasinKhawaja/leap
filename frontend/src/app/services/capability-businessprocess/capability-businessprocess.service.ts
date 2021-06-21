import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CapabilityBusinessprocess } from 'src/app/classes/capability-businessprocess/capability-businessprocess';


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

  public addCapabilityBusinessProcess(capabilityid: string, capabilitybusinessprocess: CapabilityBusinessprocess) {
    var url = `${this.cbpURL}/${capabilityid}`;
    return this.http.post<CapabilityBusinessprocess>(url, capabilitybusinessprocess.getParams(),
      { headers: this.contentHeaders })
  }

  public deleteCapabilityBusienssProcess(capabilitybusinessprocessid: string): Observable<any> {
    var url = `${this.cbpURL}/${capabilitybusinessprocessid}`;
    return this.http.delete(url)
  }
}

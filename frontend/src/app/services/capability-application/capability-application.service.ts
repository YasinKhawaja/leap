import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { tap } from "rxjs/operators";
import { CapabilityApplication } from 'src/app/classes/capability-application/capability-application';
import { Capability } from 'src/app/classes/capability/capability';

@Injectable({
  providedIn: 'root'
})
export class CapabilityApplicationService {

  private capabilityApplicationURL: string = '//localhost:8080/api/capitapp';
  private contentHeaders: HttpHeaders;

  private _refreshNeeded$ = new Subject<void>();

  get refreshNeeded$() {
    return this._refreshNeeded$;
  }

  constructor(private http: HttpClient, private router:Router) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
  }

  public getCapabilityApplications(capabilityId: string): Observable<CapabilityApplication[]> {
    let url = `${this.capabilityApplicationURL}/${capabilityId}`;
    return this.http.get<CapabilityApplication[]>(url);
  }

  public getCapabilitiesLinkedToITApplication(itApplicationName: string): Observable<Capability[]> {
    let url = `${this.capabilityApplicationURL}/linked/${itApplicationName}`;
    return this.http.get<Capability[]>(url)
                    .pipe(
                      tap(() => {
                        this._refreshNeeded$.next();
                      })
                    );
  }

  public getCapabilityApplication(capabilityId: string): Observable<CapabilityApplication> {
    var url = `${this.capabilityApplicationURL}/searchOne/${capabilityId}`
    return this.http.get<CapabilityApplication>(url);
  }


  public createCapabilityApplication(capabilityId: string, capabilityApplication: CapabilityApplication): Observable<any>{
    let url = `${this.capabilityApplicationURL}/${capabilityId}`;
    return this.http.post<CapabilityApplication>(url, capabilityApplication.getParams());
   
  }

  public updateCapabilityApplication(capabilityApplicationID: string, capabilityApplication: CapabilityApplication): Observable<any> {
    let url = `${this.capabilityApplicationURL}/${capabilityApplicationID}`

   return this.http.put<CapabilityApplication>(url, capabilityApplication.getParams());
   
  }

  public deleteCapabilityApplication(capabilityApplicationID: string) {
    let url = `${this.capabilityApplicationURL}/${capabilityApplicationID}`

   return this.http.delete(url);
    
  }
}

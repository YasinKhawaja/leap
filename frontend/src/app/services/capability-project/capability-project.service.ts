import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CapabilityProject } from 'src/app/classes/capability-project/capability-project';


@Injectable({
  providedIn: 'root'
})
export class CapabilityProjectService {

  private cpURL: string = '//localhost:8080/api/capabilityproject'
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient, private router: Router) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
  }

  public getCapabilityProject(capabilityid: string): Observable<CapabilityProject[]> {
    var url = `${this.cpURL}/${capabilityid}`
    return this.http.get<CapabilityProject[]>(url);
  }

  public addCapabilityProject(capabilityid: string, capabilityproject: CapabilityProject) {
    var url = `${this.cpURL}/${capabilityid}`;
    return this.http.post<CapabilityProject>(url, capabilityproject.getParams(),
      { headers: this.contentHeaders })
  }

  public deleteCapabilityProject(capabilityprojectid: string): Observable<any> {
    var url = `${this.cpURL}/${capabilityprojectid}`
   return this.http.delete(url);
     
   
  }
}

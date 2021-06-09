import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CapabilityProject } from 'src/app/classes/capability-project/capability-project';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class CapabilityProjectService {

  private cpURL: string = '//localhost:8080/api/capabilityproject'
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient, private router: Router) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
  }

  public getCapabilityProject(capabilityid: string): Observable<CapabilityProject[]>{
    var url = `${this.cpURL}/${capabilityid}`
    return this.http.get<CapabilityProject[]>(url);
  }

  public addCapabilityProject(capabilityid: string, capabilityproject: CapabilityProject){
    var url = `${this.cpURL}/${capabilityid}`;
    return this.http.post<CapabilityProject>(url, capabilityproject.getParams(),
    {headers: this.contentHeaders})
    .subscribe(
      () => {
        this.router.navigate(['capability-project'])
      },
      () => {
        Swal.fire('Error', 'Failed to add capability-project link', 'error')
      }
    )
  }

  public deleteCapabilityProject(capabilityprojectid: string){
    var url = `${this.cpURL}/${capabilityprojectid}`
    this.http.delete(url).subscribe(
      () => {
        this.router.navigate(['capability-project'])
      }
    )
  }
}

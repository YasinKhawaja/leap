import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Itapplication } from 'src/app/classes/itapplication/itapplication';

@Injectable({
  providedIn: 'root'
})
export class ItapplicationService {

  private itApplicationURL: string = '//localhost:8080/api/itapplications';
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient, private router: Router) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/json')
  }

  public getITApplications_CurrentEnvironment(environmentId: string): Observable<Itapplication[]> {
    let url = `${this.itApplicationURL}/${environmentId}`;
    return this.http.get<Itapplication[]>(url);
  }

  public getITApplication(itapplicationId: string): Observable<Itapplication> {
    var url = `${this.itApplicationURL.slice(0, this.itApplicationURL.length - 1)}/${itapplicationId}`;

    return this.http.get<Itapplication>(url);
  }

  public createITApplication_CurrentEnvironment(environmentId: string, itApplication: Itapplication): Observable<any> {
    let url = `${this.itApplicationURL}/${environmentId}`;
    return this.http.post<Itapplication>(url, itApplication);
    
  }

  updateITApplication_CurrentEnvironment(itApplicationID: string, itApplication: Itapplication): Observable<any> {
    let url = `${this.itApplicationURL}/${itApplicationID}`

    return this.http.put<Itapplication>(url, itApplication);
    
  }

  deleteITApplication_CurrentEnvironment(itApplicationID: string) {
    let url = `${this.itApplicationURL}/${itApplicationID}`

   return this.http.delete(url);
     
  }
}

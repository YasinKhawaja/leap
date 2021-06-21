import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
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

  public addBusinessProcess(environmentid: string, businessprocess: Businessprocess) : Observable<any> {
    var url = `${this.businessprocessURL}/${environmentid}`;

    return this.http.post<Businessprocess>(url, businessprocess);
   
  }

  public updateBusinessProcess(businessprocessid: string, businessprocess: Businessprocess) : Observable<any>{
    var url = `${this.businessprocessURL}/${businessprocessid}`;

    return this.http.put<Businessprocess>(url, businessprocess);
    
  }

  public deleteBusinessProcess(businessprocessid: string) {
    var url = `${this.businessprocessURL}/${businessprocessid}`;

  return  this.http.delete(url);
     
  }
}

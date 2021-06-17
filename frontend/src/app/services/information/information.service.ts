import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Information } from 'src/app/classes/information/information';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class InformationService {

  private informationURI: string = '//localhost:8080/api/information'
  private contentheaders: HttpHeaders

  constructor(private http: HttpClient, private router: Router) {
    this.contentheaders = new HttpHeaders().set('Content-Type', 'application/json')
  }

  public getInformationList(environmentid: string): Observable<Information[]> {
    var url = `${this.informationURI}s/${environmentid}`
    return this.http.get<Information[]>(url)
  }

  public getInformation(informationid: string): Observable<Information> {
    var url = `${this.informationURI}/${informationid}`
    return this.http.get<Information>(url)
  }

  public addInformation(environmentid: string, information: Information) {
    var url = `${this.informationURI}/${environmentid}`
    return this.http.post<Information>(url, information,
      { headers: this.contentheaders })
  }

  public updateInformation(informationid: string, information: Information) {
    var url = `${this.informationURI}/${informationid}`
    return this.http.put<Information>(url, information,
      { headers: this.contentheaders })
  }

  public deleteInformation(informationid: string): Observable<any> {
    var url = `${this.informationURI}/${informationid}`
    return this.http.delete(url)
  }
}

import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CapabilityInformation } from 'src/app/classes/capability-information/capability-information';

@Injectable({
  providedIn: 'root'
})
export class CapabilityInformationService {

  private capinfoURI: string = '//localhost:8080/api/capabilityinformation'

  constructor(private http: HttpClient) { }

  getCapInfoList(capabilityid: string): Observable<CapabilityInformation[]> {
    var url = `${this.capinfoURI}/${capabilityid}`
    return this.http.get<CapabilityInformation[]>(url)
  }

  getCapInfoListInformation(informationid: string): Observable<CapabilityInformation[]> {
    var url = `${this.capinfoURI}/information/${informationid}`
    return this.http.get<CapabilityInformation[]>(url)
  }

  addCapInfo(capabilityid: string, information: string, criticality: string): Observable<any> {
    var url = `${this.capinfoURI}/${capabilityid}`

    var params = new HttpParams().set('information', information).set('criticality', criticality.toUpperCase())
    console.log(params.toString())

    return this.http.post<CapabilityInformation>(url, params)
  }

  updateCapInfo(capinfoid: string, criticality: string): Observable<any> {
    var url = `${this.capinfoURI}/${capinfoid}`

    var params = new HttpParams().set('criticality', criticality.toUpperCase())

    return this.http.put<CapabilityInformation>(url, params)
  }

  deleteCapInfo(capinfoid: string): Observable<any> {
    var url = `${this.capinfoURI}/${capinfoid}`
    return this.http.delete(url)
  }
}

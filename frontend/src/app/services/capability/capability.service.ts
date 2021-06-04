import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Capability } from '../../classes/capability/capability';

@Injectable({
  providedIn: 'root'
})
export class CapabilityService {

  private capabilitiesServiceURI: string = 'http://localhost:8080/api/capabilities';
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
  }

  // Get all capabilities
  getAllCapabilities(): Observable<Capability[]> {
    let url = `${this.capabilitiesServiceURI}`;
    return this.http.get<Capability[]>(url);
  }

  // To GET all caps in an env
  getAllCapabilitiesInEnvironment(envId: string): Observable<Capability[]> {
    var url = `${this.capabilitiesServiceURI}`;

    return this.http.get<Capability[]>(url, { params: { envId: envId } });
  }

  // To GET a cap in its env
  getCapability(envId: string, capId: string): Observable<Capability> {
    var url = `${this.capabilitiesServiceURI}/${capId}`;

    return this.http.get<Capability>(url, { params: { envId: envId } });
  }

  // To CREATE a capability in its environment
  createCapability(envId: string, parentCapId: string, cap: Capability): Observable<Capability> {
    var url = `${this.capabilitiesServiceURI}`;

    return this.http.post<Capability>(url, cap, { params: { envId: envId, parentCapId: parentCapId } });
  }

  // To UPDATE a capability in its environment
  updateCapabilityInEnvironment(envId: string, capId: string, cap: Capability): Observable<Capability> {
    var url = `${this.capabilitiesServiceURI}/${capId}`;

    return this.http.put<Capability>(url, cap, { params: { envId: envId } });
  }

  // To DELETE a cap in its env
  deleteCapability(envId: string, capId: string): Observable<{}> {
    var url = `${this.capabilitiesServiceURI}/${capId}`;

    return this.http.delete(url, { params: { envId: envId } });
  }

  // Search one capability by name
  searchOneCapability(name: string): Observable<Capability[]> {
    let url = `${this.capabilitiesServiceURI}/searchOne`

    return this.http.post<Capability[]>(url, `name=${name}`,
      { headers: this.contentHeaders })
  }


}
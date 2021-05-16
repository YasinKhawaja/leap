import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Capability } from '../../classes/capability/capability';

@Injectable({
  providedIn: 'root'
})
export class CapabilityService {

  private capabilitiesServiceURI: string = 'http://localhost:8080/api/envs'; // /{envId}/caps
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
  }

  // Get all capabilities
  getAllCapabilities(): Observable<Capability[]> {
    let url = `${this.capabilitiesServiceURI}`;
    return this.http.get<Capability[]>(url);
  }

  // To GET all caps in their env
  getAllCapabilitiesInEnvironment(envId: string): Observable<Capability[]> {
    var url = `${this.capabilitiesServiceURI}/${envId}/caps`;

    return this.http.get<Capability[]>(url);
  }

  // To GET a capability by name
  getCapabilityByName(envName: string, capName: string): Observable<Capability> {
    var url = `${this.capabilitiesServiceURI}/${envName}/caps/${capName}`;

    return this.http.get<Capability>(url);
  }

  // To CREATE a capability in its environment
  createCapabilityInEnvironment(envId: string, cap: Capability): Observable<Capability> {
    var url = `${this.capabilitiesServiceURI}/${envId}/caps`

    return this.http.post<Capability>(url, cap.getParams(), { headers: this.contentHeaders });
  }

  // To UPDATE a capability in its environment
  updateCapabilityInEnvironment(envId: string, capId: string, cap: Capability): Observable<Capability> {
    var url = `${this.capabilitiesServiceURI}/${envId}/caps/${capId}`;

    return this.http.put<Capability>(url, cap.getParams(), { headers: this.contentHeaders });
  }

  // To DELETE a cap in its env
  deleteCapabilityFromEnvironment(envId: string, capId: string): void {
    var url = `${this.capabilitiesServiceURI}/${envId}/caps/${capId}`

    this.http.delete(url).subscribe();
  }

  // Search one capability by name
  searchOneCapability(name: string): Observable<Capability[]> {
    let url = `${this.capabilitiesServiceURI}/searchOne`

    return this.http.post<Capability[]>(url, `name=${name}`,
      { headers: this.contentHeaders })
  }

}
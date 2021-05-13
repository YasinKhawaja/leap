import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';
import { Capability } from '../../classes/capability/capability';

@Injectable({
  providedIn: 'root'
})
export class CapabilityService {

  private capabilitiesServiceURI: string = 'http://localhost:8080/api/environments'; // /{envId}/capabilities
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
  }

  // Get all capabilities
  getAllCapabilities(): Observable<Capability[]> {
    let url = `${this.capabilitiesServiceURI}`;
    return this.http.get<Capability[]>(url);
  }

  // To GET a capability by name
  getCapabilityByName(envName: string, capName: string): Observable<Capability> {
    var url = `${this.capabilitiesServiceURI}/${envName}/capabilities/${capName}`;

    return this.http.get<Capability>(url);
  }

  // To CREATE a capability in its environment
  createCapabilityInEnvironment(envId: number, cap: Capability): Observable<Capability> {
    var url = `${this.capabilitiesServiceURI}/${envId}/capabilities`

    return this.http.post<Capability>(url, cap.getParams(), { headers: this.contentHeaders });
  }

  // To UPDATE a capability in its environment
  updateCapabilityInEnvironment(envId: number, capId: number, cap: Capability): Observable<Capability> {
    var url = `${this.capabilitiesServiceURI}/${envId}/capabilities/${capId}`;

    return this.http.put<Capability>(url, cap.getParams(), { headers: this.contentHeaders });
  }

  // Delete a capability
  deleteCapability(name: string): void {
    let url = `${this.capabilitiesServiceURI}/delete/${name}`
    // !!! subscribe is needed to execute DELETE
    this.http.delete(url,
      { headers: this.contentHeaders })
      .subscribe(data => { console.log(data) },
        error => { if (error.error.message) Swal.fire('Error', error.error.message, 'error') })
  }



  // Search one capability by name
  searchOneCapability(name: string): Observable<Capability[]> {
    let url = `${this.capabilitiesServiceURI}/searchOne`

    return this.http.post<Capability[]>(url, `name=${name}`,
      { headers: this.contentHeaders })
  }

}

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Capability } from './capability';

@Injectable({
  providedIn: 'root'
})
export class CapabilityService {

  private capabilitiesServiceURI: string = 'http://localhost:8080/capabilities';
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
 }

  // Get all capabilities
  getAllCapabilities(): Observable<Capability[]>{
    let url = `${this.capabilitiesServiceURI}`;
    return this.http.get<Capability[]>(url);
  }

  // Add a capability
  addCapability(capability: Capability): void {
    let url = `${this.capabilitiesServiceURI}/add`
      // !!! subscribe is needed to execute POST
    this.http.post(url, capability.getParams(),
                  { headers: this.contentHeaders})
                  .subscribe(data => { console.log(data) }, 
                  error => { console.error(error) })
  }
 
}

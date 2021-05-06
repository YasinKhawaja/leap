import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';
import { Capability } from '../../classes/capability/capability';

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
                  error => { if (error.error.message) Swal.fire('Error',error.error.message, 'error')})
  }

  // Delete a capability
  deleteCapability(name: string): void{
    let url = `${this.capabilitiesServiceURI}/delete/${name}`
    // !!! subscribe is needed to execute DELETE
    this.http.delete(url,
      { headers: this.contentHeaders})
      .subscribe(data => { console.log(data) },
                  error => { if (error.error.message) Swal.fire('Error',error.error.message, 'error')})
  }

  // Edit a capability
  editCapability(capability: Capability, originalName: string): void {
    let url = `${this.capabilitiesServiceURI}/edit/${originalName}`;
    // !! subscribe is needed to execute POST
    this.http.post(url, capability.getParams(),
                    { headers: this.contentHeaders})
                    .subscribe(data => { console.log(data) },
                                error => { console.log(error) })
  }

  // Search one capability by name
  searchOneCapability(name: string): Observable<Capability[]> {
    let url = `${this.capabilitiesServiceURI}/searchOne`

    return this.http.post<Capability[]>(url, `name=${name}`,
                    { headers: this.contentHeaders})
  }
 
}

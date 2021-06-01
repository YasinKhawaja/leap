import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resource } from 'src/app/classes/resource/resource';

@Injectable({
  providedIn: 'root'
})
export class ResourceService {

  private resourceServiceURI: string = 'http://localhost:8080/api/resources';

  constructor(private http: HttpClient) { }

  // To GET all resources
  getAllResources(): Observable<Resource[]> {
    var url = `${this.resourceServiceURI}`;

    return this.http.get<Resource[]>(url);
  }

  // To GET a resource

  // To CREATE a resource
  createResource(res: Resource): Observable<string> {
    var url = `${this.resourceServiceURI}`;

    return this.http.post<string>(url, res);
  }

}

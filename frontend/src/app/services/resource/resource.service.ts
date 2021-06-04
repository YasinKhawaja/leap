import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resource } from 'src/app/classes/resource/resource';

@Injectable({
  providedIn: 'root'
})
export class ResourceService {

  private resourceServiceURI: string = '//localhost:8080/api/resources';

  constructor(private http: HttpClient) { }

  // To GET all resources
  getAllResources(): Observable<Resource[]> {
    var url = `${this.resourceServiceURI}`;

    return this.http.get<Resource[]>(url);
  }

  // To GET a resource
  getResource(resId: string): Observable<Resource> {
    var url = `${this.resourceServiceURI}/${resId}`;

    return this.http.get<Resource>(url);
  }

  // To CREATE a resource
  createResource(res: Resource): Observable<Resource> {
    var url = `${this.resourceServiceURI}`;

    return this.http.post<Resource>(url, res);
  }

  // To UPDATE a resource
  updateResource(resId: string, resNewValues: Resource): Observable<Resource> {
    var url = `${this.resourceServiceURI}/${resId}`;

    return this.http.put<Resource>(url, resNewValues);
  }

  // To DELETE a resource
  deleteResource(resId: string): Observable<any> {
    var url = `${this.resourceServiceURI}/${resId}`;

    return this.http.delete(url);
  }

}

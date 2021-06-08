import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resource } from 'src/app/classes/resource/resource';

@Injectable({
  providedIn: 'root'
})
export class ResourceService {

  private resourceServiceURI: string = '//localhost:8080/api/resources';

  constructor(private http: HttpClient) { }

  getAllResources(environmentId: string): Observable<Resource[]> {
    var url = `${this.resourceServiceURI}`;
    var environmentIdParam = new HttpParams().set('environmentId', environmentId);

    return this.http.get<Resource[]>(url, { params: environmentIdParam });
  }

  getResource(resourceId: string, environmentId: string): Observable<Resource> {
    var url = `${this.resourceServiceURI}/${resourceId}`;
    var environmentIdParam = new HttpParams().set('environmentId', environmentId);

    return this.http.get<Resource>(url, { params: environmentIdParam });
  }

  createResource(environmentId: string, resource: Resource): Observable<Resource> {
    var url = `${this.resourceServiceURI}`;
    var environmentIdParam = new HttpParams().set('environmentId', environmentId);

    return this.http.post<Resource>(url, resource, { params: environmentIdParam });
  }

  updateResource(resourceId: string, resourceNewValues: Resource): Observable<Resource> {
    var url = `${this.resourceServiceURI}/${resourceId}`;

    return this.http.put<Resource>(url, resourceNewValues);
  }

  deleteResource(resourceId: string): Observable<any> {
    var url = `${this.resourceServiceURI}/${resourceId}`;

    return this.http.delete(url);
  }

}

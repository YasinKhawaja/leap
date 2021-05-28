import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resource } from 'src/app/classes/resource/resource';

@Injectable({
  providedIn: 'root'
})
export class ResourceService {

  private resourcesServiceURI: string = 'http://localhost:8080/api/resources';

  constructor(private http: HttpClient) { }

  // To GET all resources
  getAllResources(): Observable<Resource[]> {
    var url = `${this.resourcesServiceURI}`;

    return this.http.get<Resource[]>(url);
  }

}

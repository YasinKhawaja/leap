import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CapResource } from 'src/app/classes/capabillity-resource/capresource';

@Injectable({
  providedIn: 'root'
})
export class CapabilityResourceService {

  private capresourceServiceURI: string = 'http://localhost:8080/api/capresources';

  constructor(private http: HttpClient) { }

  // To GET all cap res links by cap id
  getAllCapResourcesByCapabilityId(capabilityID: string): Observable<CapResource[]> {
    var url = `${this.capresourceServiceURI}/capability/${capabilityID}`;

    return this.http.get<CapResource[]>(url);
  }

  // To GET all cap res links by res id
  getAllCapResourcesByResourceId(resourceID: string): Observable<CapResource[]> {
    var url = `${this.capresourceServiceURI}/resource/${resourceID}`;

    return this.http.get<CapResource[]>(url);
  }

  // To CREATE a cap res link
  createCapResource(capId: string, resId: any, numberOfResources: string): Observable<CapResource> {
    var url = `${this.capresourceServiceURI}`;

    var params;
    if (numberOfResources.length > 0) {
      params = new HttpParams().set('capId', capId).set('resId', resId).set('numberOfResources', numberOfResources);
    } else {
      params = new HttpParams().set('capId', capId).set('resId', resId);
    }

    return this.http.post<CapResource>(url, params);
  }

  // To UPDATE a cap res link
  updateCapResource(capresourceID: string, numberOfResources: string): Observable<CapResource> {
    var url = `${this.capresourceServiceURI}/${capresourceID}`;

    var param = new HttpParams().set('numberOfResources', numberOfResources);

    return this.http.put<CapResource>(url, param);
  }

  // To DELETE a cap res link
  deleteCapResource(capresourceID: string): Observable<{}> {
    var url = `${this.capresourceServiceURI}/${capresourceID}`;

    return this.http.delete<{}>(url);
  }

}

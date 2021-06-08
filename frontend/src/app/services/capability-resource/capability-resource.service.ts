import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CapResource } from 'src/app/classes/capabillity-resource/capresource';

@Injectable({
  providedIn: 'root'
})
export class CapabilityResourceService {

  private capresourceServiceURI: string = '//localhost:8080/api/capresources';

  constructor(private http: HttpClient) { }

  getAllCapResourcesByCapability(capabilityId: string): Observable<CapResource[]> {
    var url = `${this.capresourceServiceURI}/capability/${capabilityId}`;

    return this.http.get<CapResource[]>(url);
  }

  getAllCapResourcesByResource(resourceId: string): Observable<CapResource[]> {
    var url = `${this.capresourceServiceURI}/resource/${resourceId}`;

    return this.http.get<CapResource[]>(url);
  }

  createCapResource(capabilityId: string, resourceId: string, numberOfResources: string): Observable<CapResource> {
    var url = `${this.capresourceServiceURI}`;

    var params;
    if (numberOfResources.length > 0) {
      params = new HttpParams().set('capId', capabilityId).set('resId', resourceId).set('numberOfResources', numberOfResources);
    } else {
      params = new HttpParams().set('capId', capabilityId).set('resId', resourceId);
    }

    return this.http.post<CapResource>(url, params);
  }

  updateCapResource(capresourceId: string, numberOfResources: string): Observable<CapResource> {
    var url = `${this.capresourceServiceURI}/${capresourceId}`;

    var param = new HttpParams().set('numberOfResources', numberOfResources);

    return this.http.put<CapResource>(url, param);
  }

  deleteCapResource(capresourceId: string): Observable<{}> {
    var url = `${this.capresourceServiceURI}/${capresourceId}`;

    return this.http.delete<{}>(url);
  }

}

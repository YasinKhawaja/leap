import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CapResource } from 'src/app/classes/capabillity-resource/capresource';

@Injectable({
  providedIn: 'root'
})
export class CapabilityResourceService {

  private capresourceServiceURI: string = '//localhost:8080/api/capresources';

  constructor(private http: HttpClient) { }

  // To GET all cap res links by res id
  getAllCapResourcesByResourceId(resourceID: string): Observable<CapResource[]> {
    var url = `${this.capresourceServiceURI}/resource/${resourceID}`;

    return this.http.get<CapResource[]>(url);
  }

}

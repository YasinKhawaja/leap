import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { CapabilityStrategyItems } from 'src/app/classes/capability-strategyitems/capability-strategyitems';
import { Capability } from 'src/app/classes/capability/capability';
import { CapabilityStrategyitemsComponent } from 'src/app/components/capability-strategyitems/capability-strategyitems.component';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class CapabilityStrategyitemService {

  private capabilityStrategyItemURL: string = '//localhost:8080/api/capstrategyitems';
  private contentHeaders: HttpHeaders;

  private _refreshNeeded$ = new Subject<void>();

  get refreshNeeded$() {
    return this._refreshNeeded$;
  }

  constructor(private http: HttpClient, private router:Router) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
  }

  public getCapabilityStrategyItems(capabilityId: string): Observable<CapabilityStrategyItems[]> {
    let url = `${this.capabilityStrategyItemURL}/${capabilityId}`;
    return this.http.get<CapabilityStrategyItems[]>(url);
  }

  public getCapabilityStrategyItemsLinkedToStrategyItem(strategyItemName: string): Observable<CapabilityStrategyItems[]> {
    let url = `${this.capabilityStrategyItemURL}/linked/${strategyItemName}`;
    return this.http.get<CapabilityStrategyItems[]>(url)
                    .pipe(
                      tap(() => {
                        this._refreshNeeded$.next();
                      })
                    );
  }

  public createCapabilityStrategyItem(capabilityId: string, capabilityStrategyItem: CapabilityStrategyItems){
    let url = `${this.capabilityStrategyItemURL}/${capabilityId}`;
    return this.http.post<CapabilityStrategyItems>(url, capabilityStrategyItem.getParams(),
    {headers: this.contentHeaders})
    .subscribe(() => {
      this.router.navigate([`capstrategyitems/`])
    },
    error => {
      console.log(error)
    });
  }

  public updateCapabilityStrategyItem(capabilityStrategyItemID: string, capabilityApplication: CapabilityStrategyItems) {
    let url = `${this.capabilityStrategyItemURL}/${capabilityStrategyItemID}`

    this.http.put<CapabilityStrategyItems>(url, capabilityApplication.getParams(),
    {headers: this.contentHeaders})
    .subscribe(
      () => {
      Swal.fire('Success', 'You have succesfully updated the Capability-Strategy Item link', 'success')
      this.router.navigate([`capstrategyitems/`])
    },
    error => {
      Swal.fire('Error', error.error.message, 'error')
    });
  }

  public deleteCapabilityStrategyItem(capabilityStrategyItemID: string) {
    let url = `${this.capabilityStrategyItemURL}/${capabilityStrategyItemID}`

    this.http.delete(url).subscribe(
      () => {
        Swal.fire('Success', 'You have succesfully deleted the Capability-Strategy Item link', 'success')
        this.router.navigate([`capstrategyitems/`])
      });
  }
}

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Strategy } from 'src/app/classes/strategy/strategy';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class StrategyService {
  private strategiesServiceURI: string = '//localhost:8080/api/strategies';
  private contentHeaders: HttpHeaders;

  
  constructor(private http: HttpClient, private router: Router) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/json');
 }


public getAllStrategyInEnvironment(environmentId: string): Observable<Strategy[]> {
  var url = `${this.strategiesServiceURI}/${environmentId}`;

  return this.http.get<Strategy[]>(url);
}


public createStrategy(envId: string, str: Strategy) {
  var url = `${this.strategiesServiceURI}/${envId}`
  return this.http.post<Strategy>(url, str,
  {headers: this.contentHeaders})
  .subscribe(
    () => {
      this.router.navigate([`strategies/`])
    },
    () => {
      Swal.fire('Error', 'Failed to create this strategy', 'error')
    });

}

updateStrategy_CurrentEnvironment(strId: string, strategy: Strategy){
  let url = `${this.strategiesServiceURI}/${strId}`

  this.http.put<Strategy>(url, strategy,
  {headers: this.contentHeaders})
  .subscribe(data => {
    this.router.navigate([`strategies/`])
  },
    error => {
      Swal.fire('Error', 'Failed to update the strategy', 'error')
    });
}

deleteStrategy_CurrentEnvironment(strId: string) {
  let url = `${this.strategiesServiceURI}/${strId}`

  this.http.delete(url).subscribe();
}

// Search one str by name
searchOneStrategy(name: string): Observable<Strategy[]> {
  let url = `${this.strategiesServiceURI}/searchOne`

  return this.http.post<Strategy[]>(url, `name=${name}`,
    { headers: this.contentHeaders })
}
}

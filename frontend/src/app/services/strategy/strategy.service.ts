import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Strategy } from 'src/app/classes/strategy/strategy';


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


public createStrategy(envId: string, str: Strategy) : Observable<any> {
  var url = `${this.strategiesServiceURI}/${envId}`
  return this.http.post<Strategy>(url, str)

}

updateStrategy_CurrentEnvironment(strId: string, strategy: Strategy) : Observable<any> {
  let url = `${this.strategiesServiceURI}/${strId}`

    return this.http.put<Strategy>(url, strategy)
}

deleteStrategy_CurrentEnvironment(strId: string): Observable<any> {
  let url = `${this.strategiesServiceURI}/${strId}`

  return this.http.delete(url);

}


searchOneStrategy(name: string): Observable<Strategy[]> {
  let url = `${this.strategiesServiceURI}/searchOne`

  return this.http.post<Strategy[]>(url, `name=${name}`,
    { headers: this.contentHeaders })
}
}

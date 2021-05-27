import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Strategy } from 'src/app/classes/strategy/strategy';

@Injectable({
  providedIn: 'root'
})
export class StrategyService {
  private strategiesServiceURI: string = 'http://localhost:8080/api/strategies';
  private contentHeaders: HttpHeaders;

  
  constructor(private http: HttpClient) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
 }


public getAllStrategyInEnvironment(environmentId: string): Observable<Strategy[]> {
  var url = `${this.strategiesServiceURI}/${environmentId}`;

  return this.http.get<Strategy[]>(url);
}


public createStrategy(envId: string, str: Strategy) {
  var url = `${this.strategiesServiceURI}/${envId}`
  return this.http.post<Strategy>(url, str.getParams(),
  {headers: this.contentHeaders})
  .subscribe(data => {
    console.log(data)
  },
  error => {
    console.log(error)
  });

}

updateStrategy_CurrentEnvironment(strId: string, strategy: Strategy){
  let url = `${this.strategiesServiceURI}/${strId}`

  this.http.put<Strategy>(url, strategy.getParams(),
  {headers: this.contentHeaders})
  .subscribe(data => {
    console.log(data)
  },
  error => {
    console.log(error)
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

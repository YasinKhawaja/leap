import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Environment } from 'src/app/classes/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {

  private environmentsServiceURI: string = '//localhost:8080/api/environments';

  constructor(private http: HttpClient) { }

  // To GET all environments
  getAllEnvironments(): Observable<Environment[]> {
    var url = `${this.environmentsServiceURI}`;

    return this.http.get<Environment[]>(url);
  }

  // To CREATE an environment
  createEnvironment(envName: string): Observable<Environment> {
    var url = `${this.environmentsServiceURI}`;
    var env = new Environment(envName);

    var nameParam = new HttpParams().set('name', env.name);

    return this.http.post<Environment>(url, nameParam);
  }

  // To UPDATE an environment
  updateEnvironment(envId: string, newEnvName: string): Observable<Environment> {
    var url = `${this.environmentsServiceURI}/${envId}`;
    var env = new Environment(newEnvName);

    var nameParam = new HttpParams().set('name', env.name);

    return this.http.put<Environment>(url, nameParam);
  }

  // To DELETE an environment
  deleteEnvironment(envId: string): Observable<{}> {
    var url = `${this.environmentsServiceURI}/${envId}`;

    return this.http.delete(url);
  }

}
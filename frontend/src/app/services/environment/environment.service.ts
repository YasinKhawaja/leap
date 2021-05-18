import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Environment } from 'src/app/classes/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {

  private environmentsServiceURI: string = 'http://localhost:8080/api/environments';

  constructor(private http: HttpClient) { }

  // To GET all environments
  getAllEnvironments(): Observable<Environment[]> {
    var url = `${this.environmentsServiceURI}`;

    return this.http.get<Environment[]>(url);
  }

  /*getAllUsers(): Observable<UserLeap[]> {
    let url = this.environmentsServiceURI + "/"
  }*/

  // To CREATE an environment
  createEnvironment(envName: string): Observable<Environment> {
    var url = `${this.environmentsServiceURI}`;
    var env = new Environment(envName);

    return this.http.post<Environment>(url, env.getParams());
  }

  // To UPDATE an environment
  updateEnvironment(envId: string, newEnvName: string): Observable<Environment> {
    var url = `${this.environmentsServiceURI}/${envId}`;
    var env = new Environment(newEnvName);

    return this.http.put<Environment>(url, env.getParams());
  }

  // To DELETE an environment
  deleteEnvironment(envId: string): void {
    var url = `${this.environmentsServiceURI}/${envId}`;

    this.http.delete(url).subscribe();
  }

}
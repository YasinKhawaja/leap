import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Environment } from 'src/app/classes/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {

  private environmentsServiceURI: string = 'http://localhost:8080/environments';

  constructor(private http: HttpClient) { }

  // GET all environments
  getAllEnvironments(): Observable<Environment[]> {
    let url = `${this.environmentsServiceURI}`;

    var environments = this.http.get<Environment[]>(url);

    return environments;
  }

  // To CREATE an environment
  addEnvironment(environment: Environment): void {
    let url = `${this.environmentsServiceURI}/add`

    this.http.post(url, environment.getParams())
      .subscribe(data => { console.log(data) }, error => { console.error(error) })
  }

  // To DELETE an environment
  deleteEnvironment(id: number): void {
    let url = `${this.environmentsServiceURI}/${id}`;

    this.http.delete(url)
      .subscribe(data => { console.log(data) }, error => { console.error(error) });
  }

}

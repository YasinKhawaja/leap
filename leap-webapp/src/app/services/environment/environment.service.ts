import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Environment } from 'src/app/classes/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {

  private environmentsServiceURI: string = 'http://localhost:8080/environments';

  constructor(private http: HttpClient, private router: Router) { }

  // REFRESH page
  refresh(): void {
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['environments']);
    });
  }

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

  // To UPDATE an environment
  updateEnvironment(id: number, name: string): void {
    let url = `${this.environmentsServiceURI}/${id}`;

    var nameParam = new HttpParams().set('name', name);
    console.log(nameParam);

    this.http.put(url, nameParam)
      .subscribe(data => { console.log(data) }, error => { console.error(error) });
  }

  // To DELETE an environment
  deleteEnvironment(id: number): void {
    let url = `${this.environmentsServiceURI}/${id}/delete`;

    this.http.delete(url)
      .subscribe(data => { console.log(data) }, error => { console.error(error) });
  }

}

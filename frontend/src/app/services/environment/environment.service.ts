import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Environment } from 'src/app/classes/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {

  private environmentsServiceURI: string = 'http://localhost:8080/api/environments';

  constructor(private http: HttpClient) { }

  // To GET all environments
  getEnvironments(): Observable<Environment[]> {
    var url = `${this.environmentsServiceURI}`;

    return this.http.get<Environment[]>(url);
  }

  // To GET an environment by name
  getEnvironmentByName(name: string): Observable<Environment> {
    var url = `${this.environmentsServiceURI}/${name}`;

    return this.http.get<Environment>(url);
  }

  /*getAllUsers(): Observable<UserLeap[]> {
    let url = this.environmentsServiceURI + "/"
  }*/

  // To CREATE an environment
  createEnvironment(name: string): Observable<Environment> {
    var url = `${this.environmentsServiceURI}`;
    var environment = new Environment(name);

    return this.http.post<Environment>(url, environment.getParams())
      .pipe(catchError(this.handleError));
  }

  // To UPDATE an environment
  updateEnvironment(id: number, name: string): Observable<Environment> {
    var url = `${this.environmentsServiceURI}/${id}`;
    var environment = new Environment(name);

    return this.http.put<Environment>(url, environment.getParams())
      .pipe(catchError(this.handleError));
  }

  // To DELETE an environment
  deleteEnvironment(id: number): Observable<{}> {
    let url = `${this.environmentsServiceURI}/${id}`;

    return this.http.delete(url)
      .pipe(catchError(this.handleError));
  }

  // Error handling
  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred.
      console.error(`An error occurred: ${error.error}`);
    } else {
      // The backend returned an unsuccessful response code.
      console.error(`Backend returned code ${error.status}, body was: ${error.error}`);
    }
    // Return an observable with a user-facing error message.
    console.log(error);

    return throwError('Something bad happened; please try again later.');
  }

}
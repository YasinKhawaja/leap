import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Environment } from 'src/app/classes/environment/environment';
import { Userleap } from 'src/app/classes/userleap/userleap';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {

  private environmentsServiceURI: string = 'http://localhost:8080/api/environments';

  constructor(private http: HttpClient, private router: Router) { }

  // To GET all environments
  getEnvironments(): Observable<Environment[]> {
    let url = `${this.environmentsServiceURI}`;

    return this.http.get<Environment[]>(url);
  }
  
  /*getAllUsers(): Observable<UserLeap[]> {
    let url = this.environmentsServiceURI + "/"
  }*/

  // To CREATE an environment
  createEnvironment(environmentToCreate: Environment): Observable<Environment> {
    let url = `${this.environmentsServiceURI}`

    return this.http.post<Environment>(url, environmentToCreate)
      .pipe(catchError(this.handleError));
  }

  // To UPDATE an environment
  updateEnvironment(environmentIdToUpdate: number, environmentToUpdateWith: Environment): Observable<Environment> {
    let url = `${this.environmentsServiceURI}/${environmentIdToUpdate}`;

    return this.http.put<Environment>(url, environmentToUpdateWith)
      .pipe(catchError(this.handleError));
  }

  // To DELETE an environment
  deleteEnvironment(environmentIdToDelete: number): Observable<{}> {
    let url = `${this.environmentsServiceURI}/${environmentIdToDelete}`;

    return this.http.delete(url)
      .pipe(catchError(this.handleError));
  }


  // Error handling
  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // Return an observable with a user-facing error message.
    return throwError(
      'Something bad happened; please try again later.');
  }

}

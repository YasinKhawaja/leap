import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Itapplication } from 'src/app/classes/itapplication/itapplication';

@Injectable({
  providedIn: 'root'
})
export class ItapplicationService {

  private itApplicationURL: string = 'http://localhost:8080/api/itapplications';
  private contentHeaders: HttpHeaders;

  constructor(private http: HttpClient, private router: Router) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
  }

  //gets all the it applications for the current environment
  public getITApplications_CurrentEnvironment(environmentId: string): Observable<Itapplication[]> {
    let url = `${this.itApplicationURL}/${environmentId}`;
    return this.http.get<Itapplication[]>(url);
  }

  public createITApplication_CurrentEnvironment(environmentId: string, itApplication: Itapplication) {
    let url = `${this.itApplicationURL}/${environmentId}`;
    return this.http.post<Itapplication>(url, itApplication.getParams(),
    {headers: this.contentHeaders})
    .subscribe(data => {
      console.log(data)
      this.router.navigate([`itapplication/`])
    },
    error => {
      console.log(error)
    });
  }

  updateITApplication_CurrentEnvironment(itApplicationID: string, itApplication: Itapplication){
    let url = `${this.itApplicationURL}/${itApplicationID}`

    this.http.put<Itapplication>(url, itApplication.getParams(),
    {headers: this.contentHeaders})
    .subscribe(data => {
      console.log(data)
    },
    error => {
      console.log(error)
    });
  }

  deleteITApplication_CurrentEnvironment(itApplicationID: string) {
    let url = `${this.itApplicationURL}/${itApplicationID}`

    this.http.delete(url).subscribe(
      () => {
        this.router.navigate([`itapplication/`])
      }
    );
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Program } from 'src/app/classes/program/program';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class ProgramService {

  private programURL: string = '//localhost:8080/api/program'

  constructor(private http: HttpClient, private router: Router) {
  }

  public getPrograms(environmentid: string): Observable<Program[]> {
    var url = `${this.programURL}s/${environmentid}`
    return this.http.get<Program[]>(url)
  }

  public getProgram(programid: string): Observable<Program> {
    var url = `${this.programURL}/${programid}`
    return this.http.get<Program>(url)
  }

  public addProgram(environmentid: string, program: Program) : Observable<any>  {
    var url = `${this.programURL}/${environmentid}`
    return this.http.post<Program>(url, program);
    
  }

  public updateProgram(programid: string, program: Program) : Observable<any>  {
    var url = `${this.programURL}/${programid}`
    return this.http.put<Program>(url, program);
   
  }

  public deleteProgram(programid: string) {
    var url = `${this.programURL}/${programid}`
   return this.http.delete(url);
    
  }
}

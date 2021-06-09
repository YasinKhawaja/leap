import { HttpClient, HttpHeaders } from '@angular/common/http';
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
  private contentheaders: HttpHeaders

  constructor(private http:HttpClient, private router: Router) {
    this.contentheaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
  }

  public getPrograms(environmentid: string): Observable<Program[]>{
    var url = `${this.programURL}s/${environmentid}`
    return this.http.get<Program[]>(url)
  }

  public getProgram(programid: string): Observable<Program>{
    var url = `${this.programURL}/${programid}`
    return this.http.get<Program>(url)
  }

  public addProgram(environmentid: string, program: Program){
    var url = `${this.programURL}/${environmentid}`
    return this.http.post<Program>(url, program.getParams(),
    {headers: this.contentheaders})
      .subscribe(
        () => {
          this.router.navigate(['program'])
        },
        () => {
          Swal.fire('Error', `Failed to create the program: ${program.name}`, 'error')
        }
      )
  }

  public updateProgram(programid: string, program: Program){
    var url = `${this.programURL}/${programid}`
    return this.http.put<Program>(url, program.getParams(),
    {headers: this.contentheaders})
      .subscribe (
        () => {
          this.router.navigate(['program'])
        },
        () => {
          Swal.fire('Error', `Failed to update program with id: ${programid}`, 'error')
        }
      )
  }

  public deleteProgram(programid: string){
    var url = `${this.programURL}/${programid}`
    this.http.delete(url)
    .subscribe (
      () => {
        this.router.navigate(['program'])
      },
      () => {
        Swal.fire('Error', `Failed to delete the program with id: ${programid}`, 'error')
      }
    )
  }
}

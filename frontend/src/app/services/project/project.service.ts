import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Project } from 'src/app/classes/project/project';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private projectURL: string = '//localhost:8080/api/project'

  constructor(private http: HttpClient, private router: Router) {
  }

  public getProjects(programid: string): Observable<Project[]> {
    var url = `${this.projectURL}s/${programid}`
    return this.http.get<Project[]>(url)
  }

  public getProjectsEnvironment(environmentid: string): Observable<Project[]> {
    var url = `${this.projectURL}s-environment/${environmentid}`
    return this.http.get<Project[]>(url)
  }

  public getProject(projectid: string): Observable<Project> {
    var url = `${this.projectURL}/${projectid}`
    return this.http.get<Project>(url)
  }

  public addProject(programid: string, project: Project) : Observable<any>  {
    var url = `${this.projectURL}/${programid}`
    return this.http.post<Project>(url, project);
     
  }

  public updateProject(projectid: string, project: Project) : Observable<any>  {
    var url = `${this.projectURL}/${projectid}`
  return  this.http.put<Project>(url, project);
    
  }

  public deleteProject(projectid: string) {
    var url = `${this.projectURL}/${projectid}`
   return this.http.delete(url);
    
  }
}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Project } from 'src/app/classes/project/project';
import { ProjectService } from 'src/app/services/project/project.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-project-edit',
  templateUrl: './project-edit.component.html',
  styleUrls: ['./project-edit.component.css']
})
export class ProjectEditComponent implements OnInit {

  projectid: string
  project: FormGroup

  constructor(private router: Router, private ps: ProjectService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.getProject()
      .subscribe(
        result => {
          this.project = this.fb.group({
            name: [result.name, [Validators.required]],
            description: [result.description, [Validators.required]]
          })
        },
        () => {
          Swal.fire('Error', `Failed to load project with id: ${this.projectid}`, 'error')
        }
      )
  }

  private getProject(): Observable<Project>{
    this.projectid = this.router.url.split('/')[3]
    return this.ps.getProject(this.projectid)
  }

  onSubmit(){
    this.ps.updateProject(this.projectid, new Project(
      this.project.value.name,
      this.project.value.description
    ))
  }

}

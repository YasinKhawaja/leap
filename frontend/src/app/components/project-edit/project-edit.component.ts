import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Project } from 'src/app/classes/project/project';
import { ProjectService } from 'src/app/services/project/project.service';
import Swal from 'sweetalert2';
import { ProjectComponent } from '../project/project.component';

@Component({
  selector: 'app-project-edit',
  templateUrl: './project-edit.component.html',
  styleUrls: ['./project-edit.component.css']
})
export class ProjectEditComponent implements OnInit {

  projectid: string
  project: FormGroup
  @Input() projectCurrentValues: Project;

  constructor(private ps: ProjectService, private fb: FormBuilder, private pc: ProjectComponent) { }

  ngOnInit(): void {
    this.getProject()
      .subscribe(
        result => {
          this.project = this.fb.group({
            name: [result.name, [Validators.required]],
            description: [result.description, [Validators.nullValidator]]
          })
        },
        () => {
          Swal.fire('Error', `Failed to load project with id: ${this.projectid}`, 'error')
        }
      )
  }

  private getProject(): Observable<Project>{
    //this.projectid = this.router.url.split('/')[3]
    this.projectid = this.projectCurrentValues.id;
    return this.ps.getProject(this.projectid)
  }

  onSubmit(){
    this.ps.updateProject(this.projectid, new Project(
      this.project.value.name,
      this.project.value.description
    ))
    .subscribe(
      () => {
        this.pc.ngOnInit()
        this.pc.hideAll()
      },
      () => Swal.fire('Error', `Failed to edit project`, `error`)
    )

  }

  hide(): void {
    this.pc.hideAll();
  }

}

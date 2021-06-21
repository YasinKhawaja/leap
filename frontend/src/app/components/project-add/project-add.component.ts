import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Project } from 'src/app/classes/project/project';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { ProjectService } from 'src/app/services/project/project.service';
import Swal from 'sweetalert2';
import { ProjectComponent } from '../project/project.component';

@Component({
  selector: 'app-project-add',
  templateUrl: './project-add.component.html',
  styleUrls: ['./project-add.component.css']
})
export class ProjectAddComponent {


  project: FormGroup;

  ngOnInit(): void {
    this.initializeForm();
  }
  private initializeForm() {
  this.project = this.fb.group({
    name: ['', Validators.required],
    description: ['', Validators.nullValidator]
  })
}
 

  constructor(private fb: FormBuilder, private ps: ProjectService, private ns: NavbarService, private pc: ProjectComponent) { }

  onSubmit() {
    var programid = this.ns.getProgramCookie()

    var newProject = new Project(
      this.project.value.name,
      this.project.value.description
    )

    this.ps.addProject(programid, newProject)
    .subscribe(
      () => {
        this.pc.ngOnInit();
      },
      () => {
        Swal.fire('Error', `Failed to add project`, 'error')
      }
    )
  }

  hide(): void {
    this.pc.hideAll();
  }
}

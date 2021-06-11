import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Project } from 'src/app/classes/project/project';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { ProjectService } from 'src/app/services/project/project.service';

@Component({
  selector: 'app-project-add',
  templateUrl: './project-add.component.html',
  styleUrls: ['./project-add.component.css']
})
export class ProjectAddComponent {

  project = this.fb.group({
    name: ['', Validators.required],
    description: ['', Validators.nullValidator]
  })

  constructor(private fb: FormBuilder, private ps: ProjectService, private ns: NavbarService) { }

  onSubmit() {
    var programid = this.ns.getProgramCookie()

    var newProject = new Project(
      this.project.value.name,
      this.project.value.description
    )

    this.ps.addProject(programid, newProject)
  }
}

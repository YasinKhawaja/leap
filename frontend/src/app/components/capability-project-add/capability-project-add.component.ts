import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { CapabilityProject } from 'src/app/classes/capability-project/capability-project';
import { CapabilityProjectService } from 'src/app/services/capability-project/capability-project.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { ProjectService } from 'src/app/services/project/project.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-capability-project-add',
  templateUrl: './capability-project-add.component.html',
  styleUrls: ['./capability-project-add.component.css']
})
export class CapabilityProjectAddComponent implements OnInit {

  projects: string[]

  capabilityproject= this.fb.group({
    project: ['', Validators.required]
  })

  constructor(private fb: FormBuilder, private cp: CapabilityProjectService, private ns: NavbarService, private ps: ProjectService) {
    this.projects = [];
  }

  ngOnInit(): void {
    var environmentid = this.ns.getEnvironmentCookie();

    this.ps.getProjectsEnvironment(environmentid)
      .subscribe(
        result => {
          result.forEach(
            e => {
              console.log(e);
              this.projects.push(e.name);
            }
          )
        },
        () => {
          Swal.fire('Error', `Failed to load projects of environment (${environmentid})`, 'error')
        }
      )
  }

  onSubmit(){
    var capabilityid = this.ns.getCapabilityCookie();

    var newCapabilityProject = new CapabilityProject(
      this.capabilityproject.value.project
    )
    this.cp.addCapabilityProject(capabilityid, newCapabilityProject)
  }

}

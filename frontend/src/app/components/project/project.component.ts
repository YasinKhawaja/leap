import { Component, OnInit } from '@angular/core';
import { Project } from 'src/app/classes/project/project';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { ProjectService } from 'src/app/services/project/project.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  projects: Project[];

  constructor(private ps: ProjectService, private ns: NavbarService) { }

  ngOnInit(): void {
    var program = this.ns.getProgramCookie()
    this.ps.getProjects(program)
      .subscribe(
        result => {
          this.projects = result;
        },
        () => {
          Swal.fire('Error', `Failed to load data for program: ${program}`, 'error')
        }
      )
  }

}

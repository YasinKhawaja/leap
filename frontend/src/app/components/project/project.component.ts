import { Component, OnInit } from '@angular/core';
import { Project } from 'src/app/classes/project/project';
import { JwtService } from 'src/app/services/jwt/jwt.service';
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

  constructor(private ps: ProjectService, private ns: NavbarService, public jwt: JwtService) { }

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

  showProjectAdd: boolean = false;

  showProjectEdit: boolean = false;

  showProjectDelete: boolean = false;
  projectCurrentValues: Project;
  show(component: string, project?: Project): void {
    switch (component) {
      case 'project-add':
        this.hideAll();
        // Show
        this.showProjectAdd = true;
        break;
      case 'project-edit':
        // Hide
        this.showProjectAdd = false;
        this.showProjectDelete = false;
        // Show
        this.projectCurrentValues = project;
        this.showProjectEdit = !this.showProjectEdit;
        break;
        case 'project-delete':
          // Hide
          this.showProjectAdd = false;
          this.showProjectEdit = false;
          // Show
          this.projectCurrentValues = project;
          this.showProjectDelete = !this.showProjectDelete;
          break;
 
      default:
        break;
    }
  }

  hideAll(): void {
    this.showProjectAdd = false;
    this.showProjectEdit = false;
    this.showProjectDelete= false;
  }

}

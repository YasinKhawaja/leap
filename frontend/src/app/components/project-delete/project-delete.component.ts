import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProjectService } from 'src/app/services/project/project.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-project-delete',
  templateUrl: './project-delete.component.html',
  styleUrls: ['./project-delete.component.css']
})
export class ProjectDeleteComponent {

  constructor(private ps: ProjectService, private router: Router, private location: Location) { }

  deleteProject() {
    var projectid = this.router.url.split('/')[3];
    this.ps.deleteProject(projectid)
  }

  navigateBack(){
    this.location.back();
  }
}

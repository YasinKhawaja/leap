import { Component, Input } from '@angular/core';
import { ProjectService } from 'src/app/services/project/project.service';
import { ProjectComponent } from '../project/project.component';
import { Project } from 'src/app/classes/project/project';

@Component({
  selector: 'app-project-delete',
  templateUrl: './project-delete.component.html',
  styleUrls: ['./project-delete.component.css']
})
export class ProjectDeleteComponent {

  @Input() projectCurrentValues: Project;
  constructor(private ps: ProjectService,private pc: ProjectComponent) { }

  deleteProject() {
    var projectid = this.projectCurrentValues.id;
    //var projectid = this.router.url.split('/')[3];
    this.ps.deleteProject(projectid)
  }

  hide(): void {
    this.pc.hideAll();
  }
}

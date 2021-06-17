import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { CapabilityProjectService } from 'src/app/services/capability-project/capability-project.service';
import { Location } from '@angular/common';
import { CapabilityProject } from 'src/app/classes/capability-project/capability-project';
import { CapabilityProjectComponent } from '../capability-project/capability-project.component';

@Component({
  selector: 'app-capability-project-delete',
  templateUrl: './capability-project-delete.component.html',
  styleUrls: ['./capability-project-delete.component.css']
})
export class CapabilityProjectDeleteComponent {

  @Input() capabilityProjectCurrentValues: CapabilityProject;
  constructor(private cp: CapabilityProjectService,private cpc : CapabilityProjectComponent) { }

  deleteCapabilityProject() {

    //let capabilityprojectid = this.router.url.split('/')[3];
    let capabilityprojectid = this.capabilityProjectCurrentValues.id;

    this.cp.deleteCapabilityProject(capabilityprojectid.toString());
  }


  hide(): void {
    this.cpc.hideAll();
  }
}

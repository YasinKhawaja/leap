import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CapabilityProjectService } from 'src/app/services/capability-project/capability-project.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-capability-project-delete',
  templateUrl: './capability-project-delete.component.html',
  styleUrls: ['./capability-project-delete.component.css']
})
export class CapabilityProjectDeleteComponent {

  constructor(private cp: CapabilityProjectService, private router: Router, private location: Location) { }

  deleteCapabilityProject() {
    let capabilityprojectid = this.router.url.split('/')[3];

    this.cp.deleteCapabilityProject(capabilityprojectid);
  }

  navigateBack() {
    this.location.back();
  }
}

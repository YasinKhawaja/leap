import { Component, OnInit } from '@angular/core';
import { CapabilityProject } from 'src/app/classes/capability-project/capability-project';
import { CapabilityProjectService } from 'src/app/services/capability-project/capability-project.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-capability-project',
  templateUrl: './capability-project.component.html',
  styleUrls: ['./capability-project.component.css']
})
export class CapabilityProjectComponent implements OnInit {

  capabilityprojects: CapabilityProject[];

  constructor(private cp: CapabilityProjectService, private ns: NavbarService) { }

  ngOnInit(): void {
    var capabilityid = this.ns.getCapabilityCookie();

    this.cp.getCapabilityProject(capabilityid)
      .subscribe(
        result => {
          this.capabilityprojects = result;
        },
        () => {
          Swal.fire('Error', `Failed to load the projects linked to capability (${capabilityid})`)
        }
      )
  }

}

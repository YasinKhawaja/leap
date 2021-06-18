import { Component, OnInit } from '@angular/core';
import { CapabilityProject } from 'src/app/classes/capability-project/capability-project';
import { Capability } from 'src/app/classes/capability/capability';
import { CapabilityProjectService } from 'src/app/services/capability-project/capability-project.service';
import { CapabilityService } from 'src/app/services/capability/capability.service';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-capability-project',
  templateUrl: './capability-project.component.html',
  styleUrls: ['./capability-project.component.css']
})
export class CapabilityProjectComponent implements OnInit {
  
  capability: Capability;
  capabilityprojects: CapabilityProject[];

  constructor(private cp: CapabilityProjectService, private ns: NavbarService, public jwt: JwtService, private cs: CapabilityService) { }

  ngOnInit(): void {
    var capabilityid = this.ns.getCapabilityCookie();
    this.getCapability();
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

  private getCapability(): void {
    var envId = this.ns.getEnvironmentCookie(), capId = this.ns.getCapabilityCookie();
    this.cs.getCapability(envId, capId).subscribe(response => this.capability = response);
  }

  showcapabilityProjectAdd: boolean = false;


  showcapabilityProjectDelete: boolean = false;
  capabilityProjectCurrentValues: CapabilityProject;
  show(component: string, capabilityProject?: CapabilityProject): void {
    switch (component) {
      case 'capbsprocess-add':
        this.hideAll();
        // Show
        this.showcapabilityProjectAdd = true;
        break;
     
        case 'capbsprocess-delete':
          // Hide
          this.showcapabilityProjectAdd = false;
          
          // Show
          this.capabilityProjectCurrentValues = capabilityProject;
          this.showcapabilityProjectDelete = !this.showcapabilityProjectDelete;
          break;
      default:
        break;
    }
  }

  hideAll(): void {
    this.showcapabilityProjectAdd = false;
   
    this.showcapabilityProjectDelete= false;
  }

}

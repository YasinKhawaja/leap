import { Component, OnInit } from '@angular/core';
import { CapabilityApplication } from 'src/app/classes/capability-application/capability-application';
import { Capability } from 'src/app/classes/capability/capability';
import { CapabilityApplicationService } from 'src/app/services/capability-application/capability-application.service';
import { CapabilityService } from 'src/app/services/capability/capability.service';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-capability-application',
  templateUrl: './capability-application.component.html',
  styleUrls: ['./capability-application.component.css']
})
export class CapabilityApplicationComponent implements OnInit {

  capability: Capability;

  capabilityApplications: CapabilityApplication[];

  constructor(private cas: CapabilityApplicationService, private ns: NavbarService, public jwt: JwtService, private cs: CapabilityService) { }

  ngOnInit(): void {
    this.getCapability();
    let capabilityId = this.ns.getCapabilityCookie();

    this.cas.getCapabilityApplications(capabilityId)
      .subscribe(
        result => {
          this.capabilityApplications = result;
          console.log(this.capabilityApplications);
        },
        error => {
          Swal.fire('Error', error.error.message, 'error')
        });
  }

  private getCapability(): void {
    var envId = this.ns.getEnvironmentCookie(), capId = this.ns.getCapabilityCookie();
    this.cs.getCapability(envId, capId).subscribe(response => this.capability = response);
  }

  showCapAppAdd: boolean = false;

  showCapAppEdit: boolean = false;

  showCapAppDelete: boolean = false;
  capAppCurrentValues: CapabilityApplication;
  show(component: string, capapp?: CapabilityApplication): void {
    switch (component) {
      case 'capapp-add':
        this.hideAll();
        // Show
        this.showCapAppAdd = true;
        break;
      case 'capapp-edit':
        // Hide
        this.showCapAppAdd = false;
        this.showCapAppDelete = false;
        // Show
        this.capAppCurrentValues = capapp;
        this.showCapAppEdit = !this.showCapAppEdit;
        break;
        case 'capapp-delete':
          // Hide
          this.showCapAppAdd = false;
          this.showCapAppEdit = false;
          // Show
          this.capAppCurrentValues = capapp;
          this.showCapAppDelete = !this.showCapAppDelete;
          break;
 
      default:
        break;
    }
  }

  hideAll(): void {
    this.showCapAppAdd = false;
    this.showCapAppEdit = false;
    this.showCapAppDelete= false;
  }
}

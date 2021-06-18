import { Component, OnInit } from '@angular/core';
import { CapabilityBusinessprocess } from 'src/app/classes/capability-businessprocess/capability-businessprocess';
import { Capability } from 'src/app/classes/capability/capability';
import { CapabilityBusinessprocessService } from 'src/app/services/capability-businessprocess/capability-businessprocess.service';
import { CapabilityService } from 'src/app/services/capability/capability.service';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-capability-businessprocess',
  templateUrl: './capability-businessprocess.component.html',
  styleUrls: ['./capability-businessprocess.component.css']
})
export class CapabilityBusinessprocessComponent implements OnInit {

  capability: Capability;
  capabilityBusinessProcesses: CapabilityBusinessprocess[];
  constructor(private cbp: CapabilityBusinessprocessService, private ns: NavbarService, public jwt: JwtService, private cs: CapabilityService) { }

  ngOnInit(): void {
    var capabilityid = this.ns.getCapabilityCookie();

    this.getCapability();
    this.cbp.getCapabilityBusinessProcess(capabilityid)
      .subscribe(
        result => {
          this.capabilityBusinessProcesses = result;
        },
        () => {
          Swal.fire('Error', `Failed to load the business processes linked to capability (${capabilityid}).`, 'error')
        }
      )
  }

  private getCapability(): void {
    var envId = this.ns.getEnvironmentCookie(), capId = this.ns.getCapabilityCookie();
    this.cs.getCapability(envId, capId).subscribe(response => this.capability = response);
  }

  showcapabilityBusinessProcessesAdd: boolean = false;


  showcapabilityBusinessProcessesDelete: boolean = false;
  capabilityBusinessProcessesCurrentValues: CapabilityBusinessprocess;
  show(component: string, capabilityBusinessProcesses?: CapabilityBusinessprocess): void {
    switch (component) {
      case 'capbsprocess-add':
        this.hideAll();
        // Show
        this.showcapabilityBusinessProcessesAdd = true;
        break;
     
        case 'capbsprocess-delete':
          // Hide
          this.showcapabilityBusinessProcessesAdd = false;
          
          // Show
          this.capabilityBusinessProcessesCurrentValues = capabilityBusinessProcesses;
          this.showcapabilityBusinessProcessesDelete = !this.showcapabilityBusinessProcessesDelete;
          break;
      default:
        break;
    }
  }

  hideAll(): void {
    this.showcapabilityBusinessProcessesAdd = false;
   
    this.showcapabilityBusinessProcessesDelete= false;
  }

}

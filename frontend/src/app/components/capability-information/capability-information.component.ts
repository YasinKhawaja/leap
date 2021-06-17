import { Component, OnInit } from '@angular/core';
import { CapabilityInformation } from 'src/app/classes/capability-information/capability-information';
import { Capability } from 'src/app/classes/capability/capability';
import { CapabilityInformationService } from 'src/app/services/capability-information/capability-information.service';
import { CapabilityService } from 'src/app/services/capability/capability.service';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-capability-information',
  templateUrl: './capability-information.component.html',
  styleUrls: ['./capability-information.component.css']
})
export class CapabilityInformationComponent implements OnInit {

  capability: Capability
  capinfolist: CapabilityInformation[]

  constructor(private cis: CapabilityInformationService, private cs: CapabilityService, private ns: NavbarService, public jwt: JwtService) { }

  ngOnInit(): void {
    this.getCapability()
    this.getCapInfoList()
  }

  private getCapability() {
    var envid = this.ns.getEnvironmentCookie()
    var capid = this.ns.getCapabilityCookie()
    this.cs.getCapability(envid, capid)
      .subscribe(
        (data) => {
          this.capability = data
        },
        () => {
          Swal.fire('Error', `Failed to load capability with id ${capid}`, 'error')
        }
      )
  }

  private getCapInfoList() {
    var capid = this.ns.getCapabilityCookie()
    this.cis.getCapInfoList(capid)
      .subscribe(
        (data) => {
          this.capinfolist = data
        },
        () => {
          Swal.fire('Error', `Failed to load capability info link for capability with id ${capid}`, 'error')
        }
      )
  }

  showCapInfoAdd: boolean = false
  showCapInfoEdit: boolean = false
  showCapInfoDelete: boolean = false
  capinfoCurrentValues: CapabilityInformation

  show(component: string, capinfo?: CapabilityInformation): void {
    switch (component) {
      case 'capability-information-add':
        this.hideAll();

        this.showCapInfoAdd = true;
        break;
      case 'capability-information-edit':
        this.showCapInfoAdd = false;
        this.showCapInfoDelete = false;

        this.capinfoCurrentValues = capinfo;
        this.showCapInfoEdit = !this.showCapInfoEdit;
        break;
      case 'capability-information-delete':
        this.showCapInfoAdd = false;
        this.showCapInfoEdit = false;

        this.capinfoCurrentValues = capinfo;
        this.showCapInfoDelete = !this.showCapInfoDelete;
        break;
      default:
        break;
    }
  }

  hideAll(): void {
    this.showCapInfoAdd = false;
    this.showCapInfoEdit = false;
    this.showCapInfoDelete = false;
  }
}

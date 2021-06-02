import { Component, OnInit } from '@angular/core';
import { CapabilityApplication } from 'src/app/classes/capability-application/capability-application';
import { CapabilityApplicationService } from 'src/app/services/capability-application/capability-application.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-capability-application',
  templateUrl: './capability-application.component.html',
  styleUrls: ['./capability-application.component.css']
})
export class CapabilityApplicationComponent implements OnInit {

  capabilityApplications: CapabilityApplication[];

  constructor(private cas: CapabilityApplicationService, private ns: NavbarService) { }

  ngOnInit(): void {
    let capabilityId = this.ns.getCapability();

    this.cas.getCapabilityApplications(capabilityId)
      .subscribe(
        result => {
          this.capabilityApplications = result;
        },
        error => {
          Swal.fire('Error', error.error.message, 'error')
        });
  }

}

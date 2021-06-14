import { Component, OnInit } from '@angular/core';
import { CapabilityApplication } from 'src/app/classes/capability-application/capability-application';
import { CapabilityApplicationService } from 'src/app/services/capability-application/capability-application.service';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-capability-application',
  templateUrl: './capability-application.component.html',
  styleUrls: ['./capability-application.component.css']
})
export class CapabilityApplicationComponent implements OnInit {

  capabilityApplications: CapabilityApplication[];

  constructor(private cas: CapabilityApplicationService, private ns: NavbarService, public jwt: JwtService) { }

  ngOnInit(): void {
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

}

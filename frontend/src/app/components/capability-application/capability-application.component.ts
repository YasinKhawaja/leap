import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CapabilityApplication } from 'src/app/classes/capability-application/capability-application';
import { CapabilityApplicationService } from 'src/app/services/capability-application/capability-application.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

@Component({
  selector: 'app-capability-application',
  templateUrl: './capability-application.component.html',
  styleUrls: ['./capability-application.component.css']
})
export class CapabilityApplicationComponent implements OnInit {

  capabilityApplications: CapabilityApplication[];

  constructor(private cas: CapabilityApplicationService, private router: Router, private ns: NavbarService) { }

  ngOnInit(): void {
    let capabilityId = this.ns.getCapability();

    this.cas.getCapabilityApplications(capabilityId)
      .subscribe(
        result => {
          console.log(result);
          this.capabilityApplications = result;
          //this.router.navigate([])
        },
        error => console.log(error));
  }

}

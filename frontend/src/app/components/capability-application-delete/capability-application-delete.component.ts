import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CapabilityApplicationService } from 'src/app/services/capability-application/capability-application.service';

@Component({
  selector: 'app-capability-application-delete',
  templateUrl: './capability-application-delete.component.html',
  styleUrls: ['./capability-application-delete.component.css']
})
export class CapabilityApplicationDeleteComponent implements OnInit {

  constructor(private cas: CapabilityApplicationService, private router: Router, private location: Location) { }

  ngOnInit(): void {
  }

  deleteCapability_ITApplication() {
    let capabilityITApplicationId = this.router.url.split('/')[3];

    this.cas.deleteCapabilityApplication(capabilityITApplicationId);

    this.navigateBack();
  }

  navigateBack() {
    this.location.back();
  }
}

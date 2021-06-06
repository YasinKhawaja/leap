import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CapabilityApplicationService } from 'src/app/services/capability-application/capability-application.service';
import { ItapplicationService } from 'src/app/services/itapplication/itapplication.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

@Component({
  selector: 'app-itapplication-delete',
  templateUrl: './itapplication-delete.component.html',
  styleUrls: ['./itapplication-delete.component.css']
})
export class ItapplicationDeleteComponent {

  constructor(private its: ItapplicationService, private router: Router, private location: Location, private ns: NavbarService, private cas: CapabilityApplicationService) { }

  deleteITApplication_CurrentEnvironment() {
    let capabilityId = this.ns.readCookie("Capability");
    let itApplicationId = this.router.url.split('/')[2];

    this.cas.deleteCapabilityApplication(itApplicationId + capabilityId);
    
    this.its.deleteITApplication_CurrentEnvironment(itApplicationId);
  }

  navigateBack() {
    this.location.back();
  }
}

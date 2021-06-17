import { Location } from '@angular/common';
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Itapplication } from 'src/app/classes/itapplication/itapplication';
import { CapabilityApplicationService } from 'src/app/services/capability-application/capability-application.service';
import { ItapplicationService } from 'src/app/services/itapplication/itapplication.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { ItapplicationComponent } from '../itapplication/itapplication.component';

@Component({
  selector: 'app-itapplication-delete',
  templateUrl: './itapplication-delete.component.html',
  styleUrls: ['./itapplication-delete.component.css']
})
export class ItapplicationDeleteComponent {

  @Input() itCurrentValues: Itapplication;


  constructor(private its: ItapplicationService, private router: Router, private location: Location, private ns: NavbarService, private cas: CapabilityApplicationService 
    ,private ic : ItapplicationComponent) { }

  deleteITApplication_CurrentEnvironment() {
    let capabilityId = this.ns.readCookie("Capability");
   // let itApplicationId = this.router.url.split('/')[2];
    let itApplicationId = this.itCurrentValues.id;

    this.cas.deleteCapabilityApplication(itApplicationId + capabilityId);
    
    this.its.deleteITApplication_CurrentEnvironment(itApplicationId.toString());
  }

  navigateBack() {
    this.location.back();
  }

  hide(): void {
    this.ic.hideAll();
  }
}

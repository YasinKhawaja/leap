import { Component, Input, OnInit } from '@angular/core';
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
export class ItapplicationDeleteComponent implements OnInit {

  @Input() itCurrentValues: Itapplication;


  constructor(private its: ItapplicationService, private ns: NavbarService, private cas: CapabilityApplicationService 
    ,private ic : ItapplicationComponent) { }

  deleteITApplication_CurrentEnvironment() {
    let capabilityId = this.ns.readCookie("Capability");
  
    let itApplicationId = this.itCurrentValues.id;

    this.cas.deleteCapabilityApplication(itApplicationId + capabilityId);
    
    this.its.deleteITApplication_CurrentEnvironment(itApplicationId.toString())
    .subscribe(
      () => {
        this.ic.ngOnInit()
        this.ic.hideAll()
      }
    )
  }
  ngOnInit(): void {
  }

  hide(): void {
    this.ic.hideAll();
  }
}

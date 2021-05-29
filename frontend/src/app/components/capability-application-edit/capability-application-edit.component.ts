import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CapabilityApplication } from 'src/app/classes/capability-application/capability-application';
import { CapabilityApplicationService } from 'src/app/services/capability-application/capability-application.service';
import { ItapplicationService } from 'src/app/services/itapplication/itapplication.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

@Component({
  selector: 'app-capability-application-edit',
  templateUrl: './capability-application-edit.component.html',
  styleUrls: ['./capability-application-edit.component.css']
})
export class CapabilityApplicationEditComponent implements OnInit {

  capabilityApplication = this.fb.group({
    businessEfficiencySupport: ['', Validators.required],
    businessFunctionalCoverage: ['', Validators.required],
    businessCorrectness: ['', Validators.required],
    businessFuturePotential: ['', Validators.required],
    informationCompleteness: ['', Validators.required],
    informationCorrectness: ['', Validators.required],
    informationAvailability: ['', Validators.required]
  })

  constructor(private fb: FormBuilder, private router: Router, private cas: CapabilityApplicationService, private ns: NavbarService, private its: ItapplicationService) {}

  //load in all the data from the previous capabilityApplication
  ngOnInit(): void {
  }

  onSubmit(){
  
    let capabilityApplicationId = this.router.url.split('/')[3];
    
    var newCapabilityApplication = new CapabilityApplication(
      "",
      this.capabilityApplication.value.businessEfficiencySupport,
      this.capabilityApplication.value.businessFunctionalCoverage,
      this.capabilityApplication.value.businessCorrectness,
      this.capabilityApplication.value.businessFuturePotential,
      this.capabilityApplication.value.informationCompleteness,
      this.capabilityApplication.value.informationCorrectness,
      this.capabilityApplication.value.informationAvailability
    );

    this.cas.updateCapabilityApplication(capabilityApplicationId, newCapabilityApplication);

  }

}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CapabilityApplication } from 'src/app/classes/capability-application/capability-application';
import { Itapplication } from 'src/app/classes/itapplication/itapplication';
import { CapabilityApplicationService } from 'src/app/services/capability-application/capability-application.service';
import { ItapplicationService } from 'src/app/services/itapplication/itapplication.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

@Component({
  selector: 'app-capability-application-add',
  templateUrl: './capability-application-add.component.html',
  styleUrls: ['./capability-application-add.component.css']
})
export class CapabilityApplicationAddComponent implements OnInit {

  itApplications: string[]

  capabilityApplication = this.fb.group({
    application: ['', Validators.required],
    businessEfficiencySupport: ['', Validators.required],
    businessFunctionalCoverage: ['', Validators.required],
    businessCorrectness: ['', Validators.required],
    businessFuturePotential: ['', Validators.required],
    informationCompleteness: ['', Validators.required],
    informationCorrectness: ['', Validators.required],
    informationAvailability: ['', Validators.required]
  })

  constructor(private fb: FormBuilder, private router: Router, private cas: CapabilityApplicationService, private ns: NavbarService, private its: ItapplicationService) {
    this.itApplications = [];
   }

  ngOnInit(): void {
    let environmentId = this.ns.getEnvironment();
    this.its.getITApplications_CurrentEnvironment(environmentId)
    .subscribe(result => {
      result.forEach(e => {
        this.itApplications.push(e.name);
      })
      console.log(result);
    },
    error => console.log(error));
  }

  onSubmit(){
    let capabilityId = this.ns.getCapability();
    
    var newCapabilityApplication = new CapabilityApplication(
      this.capabilityApplication.value.application,
      this.capabilityApplication.value.businessEfficiencySupport,
      this.capabilityApplication.value.businessFunctionalCoverage,
      this.capabilityApplication.value.businessCorrectness,
      this.capabilityApplication.value.businessFuturePotential,
      this.capabilityApplication.value.informationCompleteness,
      this.capabilityApplication.value.informationCorrectness,
      this.capabilityApplication.value.informationAvailability
    );

    this.cas.createCapabilityApplication(capabilityId, newCapabilityApplication);
  }

}

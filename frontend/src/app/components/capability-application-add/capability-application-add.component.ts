import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { CapabilityApplication } from 'src/app/classes/capability-application/capability-application';
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
    businessEfficiencySupport: ['', [Validators.required, Validators.pattern('[0-5]')]],
    businessFunctionalCoverage: ['', [Validators.required, Validators.pattern('[0-5]')]],
    businessCorrectness: ['', [Validators.required, Validators.pattern('[0-5]')]],
    businessFuturePotential: ['', [Validators.required, Validators.pattern('[0-5]')]],
    informationCompleteness: ['', [Validators.required, Validators.pattern('[0-5]')]],
    informationCorrectness: ['', [Validators.required, Validators.pattern('[0-5]')]],
    informationAvailability: ['', [Validators.required, Validators.pattern('[0-5]')]],
  })

  constructor(private fb: FormBuilder, private cas: CapabilityApplicationService, private ns: NavbarService, private its: ItapplicationService) {
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
    let capabilityId = this.ns.getCapabilityCookie();
    
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

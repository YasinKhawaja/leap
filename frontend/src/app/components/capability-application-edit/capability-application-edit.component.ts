import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { CapabilityApplication } from 'src/app/classes/capability-application/capability-application';
import { CapabilityApplicationService } from 'src/app/services/capability-application/capability-application.service';
import Swal from 'sweetalert2';
import { CapabilityApplicationComponent } from '../capability-application/capability-application.component';

@Component({
  selector: 'app-capability-application-edit',
  templateUrl: './capability-application-edit.component.html',
  styleUrls: ['./capability-application-edit.component.css']
})
export class CapabilityApplicationEditComponent implements OnInit{

  

  @Input() capAppCurrentValues: CapabilityApplication;

  capabilityApplication = this.fb.group({
    businessEfficiencySupport: ['', [Validators.required, Validators.pattern('[0-5]')]],
    businessFunctionalCoverage: ['', [Validators.required, Validators.pattern('[0-5]')]],
    businessCorrectness: ['', [Validators.required, Validators.pattern('[0-5]')]],
    businessFuturePotential: ['', [Validators.required, Validators.pattern('[0-5]')]],
    informationCompleteness: ['', [Validators.required, Validators.pattern('[0-5]')]],
    informationCorrectness: ['', [Validators.required, Validators.pattern('[0-5]')]],
    informationAvailability: ['', [Validators.required, Validators.pattern('[0-5]')]],
    importanceFactor: ['', [Validators.required, Validators.pattern('[0-9]?[0-9]?[0]?')]]
  })

  constructor(private fb: FormBuilder,  private cas: CapabilityApplicationService,
    private cac : CapabilityApplicationComponent) {}
  ngOnInit(): void {
    this.getCurrentCapabilityApplication()
      .subscribe( result => {
        this.capabilityApplication.setValue({
          businessEfficiencySupport: result.businessEfficiencySupport,
          businessFunctionalCoverage: result.businessFunctionalCoverage,
          businessCorrectness: result.businessCorrectness,
          businessFuturePotential: result.businessFuturePotential,
          informationCompleteness: result.informationCompleteness,
          informationCorrectness: result.informationCorrectness,
          informationAvailability: result.informationAvailability,
          importanceFactor: result.importance
        })
      })
  }

  private getCurrentCapabilityApplication(): Observable<CapabilityApplication> {
   
    let capabilityApplicationId = this.capAppCurrentValues.id;

    return this.cas.getCapabilityApplication(capabilityApplicationId.toString());
  }

  onSubmit(){
  
   
    let capabilityApplicationId = this.capAppCurrentValues.id;
    
    var newCapabilityApplication = new CapabilityApplication(
      "",
      this.capabilityApplication.value.businessEfficiencySupport,
      this.capabilityApplication.value.businessFunctionalCoverage,
      this.capabilityApplication.value.businessCorrectness,
      this.capabilityApplication.value.businessFuturePotential,
      this.capabilityApplication.value.informationCompleteness,
      this.capabilityApplication.value.informationCorrectness,
      this.capabilityApplication.value.informationAvailability,
      this.capabilityApplication.value.importanceFactor
    );

    this.cas.updateCapabilityApplication(capabilityApplicationId.toString(), newCapabilityApplication)
    .subscribe(
      () => {
        this.cac.ngOnInit()
        this.cac.hideAll()
      },
      () => Swal.fire('Error', `Failed to edit capability application link`, `error`)
    )

  }

  hide(): void {
    this.cac.hideAll();
  }

}

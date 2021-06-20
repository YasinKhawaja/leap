import { Component, Input } from '@angular/core';
import { CapabilityApplication } from 'src/app/classes/capability-application/capability-application';
import { CapabilityApplicationService } from 'src/app/services/capability-application/capability-application.service';
import { CapabilityApplicationComponent } from '../capability-application/capability-application.component';

@Component({
  selector: 'app-capability-application-delete',
  templateUrl: './capability-application-delete.component.html',
  styleUrls: ['./capability-application-delete.component.css']
})
export class CapabilityApplicationDeleteComponent {

  @Input() capAppCurrentValues: CapabilityApplication;

  constructor(private cas: CapabilityApplicationService,  private cac : CapabilityApplicationComponent) { }


  deleteCapability_ITApplication() {
  
    let capabilityITApplicationId = this.capAppCurrentValues.id;

    this.cas.deleteCapabilityApplication(capabilityITApplicationId.toString())
    .subscribe(
      () => {
        this.cac.ngOnInit()
        this.cac.hideAll()
      }
    )
  }

  hide(): void {
    this.cac.hideAll();
  }

}

import { Component, Input } from '@angular/core';
import { CapabilityInformation } from 'src/app/classes/capability-information/capability-information';
import { CapabilityInformationService } from 'src/app/services/capability-information/capability-information.service';
import { CapabilityInformationComponent } from '../capability-information/capability-information.component';

@Component({
  selector: 'app-capability-information-delete',
  templateUrl: './capability-information-delete.component.html',
  styleUrls: ['./capability-information-delete.component.css']
})
export class CapabilityInformationDeleteComponent {

  @Input() capinfoCurrentValues: CapabilityInformation

  constructor(private cis: CapabilityInformationService, private cic: CapabilityInformationComponent) { }

  hide() {
    this.cic.hideAll()
  }

  deleteCapInfo() {
    this.cis.deleteCapInfo(this.capinfoCurrentValues.id)
      .subscribe(
        () => {
          this.cic.ngOnInit()
          this.cic.hideAll()
        }
      )
  }
}

import { Component, Input } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { CapabilityBusinessprocessService } from 'src/app/services/capability-businessprocess/capability-businessprocess.service';
import { CapabilityBusinessprocess } from 'src/app/classes/capability-businessprocess/capability-businessprocess';
import { CapabilityBusinessprocessComponent } from '../capability-businessprocess/capability-businessprocess.component';

@Component({
  selector: 'app-capability-businessprocess-delete',
  templateUrl: './capability-businessprocess-delete.component.html',
  styleUrls: ['./capability-businessprocess-delete.component.css']
})
export class CapabilityBusinessprocessDeleteComponent {

  @Input() capabilityBusinessProcessesCurrentValues: CapabilityBusinessprocess;
  constructor(private cbs: CapabilityBusinessprocessService, private cpc: CapabilityBusinessprocessComponent) { }

  deleteCapabilityBusinessprocess() {
    var capabilityBusinessprocessid = this.capabilityBusinessProcessesCurrentValues.id;
    this.cbs.deleteCapabilityBusienssProcess(capabilityBusinessprocessid.toString())
      .subscribe(
        () => {
          this.cpc.ngOnInit()
          this.hide()
        }
      );
  }

  hide(): void {
    this.cpc.hideAll();
  }
}

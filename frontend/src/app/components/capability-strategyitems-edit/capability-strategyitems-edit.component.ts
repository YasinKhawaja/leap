import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CapabilityStrategyItems } from 'src/app/classes/capability-strategyitems/capability-strategyitems';
import { CapabilityStrategyitemService } from 'src/app/services/capability-strategyitem/capability-strategyitem.service';
import Swal from 'sweetalert2';
import { CapabilityStrategyitemsComponent } from '../capability-strategyitems/capability-strategyitems.component';

enum StrategicEmphasis {

  NONE = 'NONE',
  LOW = 'LOW',
  MEDIUM = 'MEDIUM',
  HIGH = 'HIGH'
}

@Component({
  selector: 'app-capability-strategyitems-edit',
  templateUrl: './capability-strategyitems-edit.component.html',
  styleUrls: ['./capability-strategyitems-edit.component.css']
})
export class CapabilityStrategyitemsEditComponent {

  eSe = StrategicEmphasis;

  @Input() capStrategyItemCurrentValues: CapabilityStrategyItems;

  capabilityStrategyItem = this.fb.group({
    strategicEmphasis: ['', Validators.required]
  })
  constructor(private fb: FormBuilder, private router: Router, private csi: CapabilityStrategyitemService , private csic : CapabilityStrategyitemsComponent) { }

  onSubmit(){
  
    //let capabilityStrategyItemID = this.router.url.split('/')[3];
    
    var newCapabilityStrategyItem = new CapabilityStrategyItems(
      "",
      this.capabilityStrategyItem.value.strategicEmphasis
    );

    this.csi.updateCapabilityStrategyItem(this.capStrategyItemCurrentValues.id.toString(), newCapabilityStrategyItem)
    .subscribe(
      () => {
        this.csic.ngOnInit()
        this.csic.hideAll()
      },
      () => Swal.fire('Error', `Failed to edit capability strategy item link`, `error`)
    )

  }

  hide(): void {
    this.csic.hideAll();
  }

}

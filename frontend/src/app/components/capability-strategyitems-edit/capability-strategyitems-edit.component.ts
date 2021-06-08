import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CapabilityStrategyItems } from 'src/app/classes/capability-strategyitems/capability-strategyitems';
import { CapabilityStrategyitemService } from 'src/app/services/capability-strategyitem/capability-strategyitem.service';

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

  capabilityStrategyItem = this.fb.group({
    strategicEmphasis: ['', Validators.required]
  })
  constructor(private fb: FormBuilder, private router: Router, private csi: CapabilityStrategyitemService) { }

  onSubmit(){
  
    let capabilityStrategyItemID = this.router.url.split('/')[3];
    
    var newCapabilityStrategyItem = new CapabilityStrategyItems(
      "",
      this.capabilityStrategyItem.value.strategicEmphasis
    );

    this.csi.updateCapabilityStrategyItem(capabilityStrategyItemID, newCapabilityStrategyItem);

  }

}

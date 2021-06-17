import { Component, Input, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { CapabilityStrategyItems } from 'src/app/classes/capability-strategyitems/capability-strategyitems';
import { Router } from '@angular/router';
import { CapabilityStrategyitemService } from 'src/app/services/capability-strategyitem/capability-strategyitem.service';
import { CapabilityStrategyitemsComponent } from '../capability-strategyitems/capability-strategyitems.component';

@Component({
  selector: 'app-capability-strategyitems-delete',
  templateUrl: './capability-strategyitems-delete.component.html',
  styleUrls: ['./capability-strategyitems-delete.component.css']
})
export class CapabilityStrategyitemsDeleteComponent implements OnInit {

  @Input() capStrategyItemCurrentValues: CapabilityStrategyItems;
  constructor(private csi: CapabilityStrategyitemService,private csic : CapabilityStrategyitemsComponent ) { }

  ngOnInit(): void {
  }

  deleteCapability_StrategyItem() {
    //let capabilityStrategyItemID = this.router.url.split('/')[3];

    this.csi.deleteCapabilityStrategyItem(this.capStrategyItemCurrentValues.id.toString());

  }

  hide(): void {
    this.csic.hideAll();
  }

}

import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { CapabilityStrategyItems } from 'src/app/classes/capability-strategyitems/capability-strategyitems';
import { Router } from '@angular/router';
import { CapabilityStrategyitemService } from 'src/app/services/capability-strategyitem/capability-strategyitem.service';

@Component({
  selector: 'app-capability-strategyitems-delete',
  templateUrl: './capability-strategyitems-delete.component.html',
  styleUrls: ['./capability-strategyitems-delete.component.css']
})
export class CapabilityStrategyitemsDeleteComponent implements OnInit {

  constructor(private csi: CapabilityStrategyitemService, private router: Router, private location: Location) { }

  ngOnInit(): void {
  }

  deleteCapability_StrategyItem() {
    let capabilityStrategyItemID = this.router.url.split('/')[3];

    this.csi.deleteCapabilityStrategyItem(capabilityStrategyItemID);

    this.navigateBack();
  }

  navigateBack() {
    this.location.back();
  }

}

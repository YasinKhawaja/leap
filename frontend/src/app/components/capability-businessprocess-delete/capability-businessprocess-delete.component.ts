import { Component } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { CapabilityBusinessprocessService } from 'src/app/services/capability-businessprocess/capability-businessprocess.service';

@Component({
  selector: 'app-capability-businessprocess-delete',
  templateUrl: './capability-businessprocess-delete.component.html',
  styleUrls: ['./capability-businessprocess-delete.component.css']
})
export class CapabilityBusinessprocessDeleteComponent {

  constructor(private cbs: CapabilityBusinessprocessService, private router: Router, private location: Location) { }

  deleteCapabilityBusinessprocess() {
    var capabilityBusinessprocessid = this.router.url.split('/')[3];

    this.cbs.deleteCapabilityBusienssProcess(capabilityBusinessprocessid);
  }

  navigateBack() {
    this.location.back();
  }
}

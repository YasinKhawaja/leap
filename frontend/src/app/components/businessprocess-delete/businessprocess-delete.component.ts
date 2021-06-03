import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BusinessprocessService } from 'src/app/services/businessprocess.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-businessprocess-delete',
  templateUrl: './businessprocess-delete.component.html',
  styleUrls: ['./businessprocess-delete.component.css']
})
export class BusinessprocessDeleteComponent  {

  constructor(private bps: BusinessprocessService, private router: Router, private location: Location) { }

  deleteBusinessProcess() {
    var businessprocessid = this.router.url.split('/')[3];

    this.bps.deleteBusinessProcess(businessprocessid);
  }

  navigateBack(){
    this.location.back();
  }
}

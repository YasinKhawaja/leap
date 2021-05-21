import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CapabilityService } from '../../services/capability/capability.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-capability-delete',
  templateUrl: './capability-delete.component.html',
  styleUrls: ['./capability-delete.component.css']
})
export class CapabilityDeleteComponent implements OnInit {

  constructor(private cs: CapabilityService, private router: Router, private location: Location) { }

  ngOnInit(): void { }

  deleteCapabilityFromEnvironment() {
    var envId = this.router.url.split('/')[2];
    var capIdToDelete = this.router.url.split('/')[4];

    this.cs.deleteCapabilityFromEnvironment(envId, capIdToDelete);

    this.navigateBack();
  }

  navigateBack() {
    this.location.back();
  }

}

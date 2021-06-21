import { Location } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Capability } from 'src/app/classes/capability/capability';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import Swal from 'sweetalert2';
import { CapabilityService } from '../../services/capability/capability.service';

@Component({
  selector: 'app-capability-delete',
  templateUrl: './capability-delete.component.html',
  styleUrls: ['./capability-delete.component.css']
})
export class CapabilityDeleteComponent implements OnInit {

  envId: string;
  capId: string;

  cap: Capability;

  constructor(private cs: CapabilityService, private router: Router, private location: Location, private ns: NavbarService) {
    this.envId = this.ns.getEnvironmentCookie();
    this.capId = this.ns.getCapabilityCookie();
  }

  ngOnInit(): void {
    this.cs.getCapability(this.envId, this.capId)
      .subscribe(
        response => this.cap = response,
        (error: HttpErrorResponse) => {
          Swal.fire('Error', error.error, 'error')
        });
  }

  // To delete the cap in its env
  deleteCapability() {
    this.cs.deleteCapability(this.envId, this.capId)
      .subscribe(
        () => this.navigateBack()
      );
  }

  // To navigate to the previous page
  navigateBack() {
    this.location.back();
  }

}
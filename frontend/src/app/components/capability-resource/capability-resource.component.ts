import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Capability } from 'src/app/classes/capability/capability';
import { CapResource } from 'src/app/classes/capabillity-resource/capresource';
import { Resource } from 'src/app/classes/resource/resource';
import { CapabilityResourceService } from 'src/app/services/capability-resource/capability-resource.service';
import { CapabilityService } from 'src/app/services/capability/capability.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { ResourceComponent } from '../resource/resource.component';

@Component({
  selector: 'app-capability-resource',
  templateUrl: './capability-resource.component.html',
  styleUrls: ['./capability-resource.component.css']
})
export class CapabilityResourceComponent implements OnInit {

  capability: Capability; // For title
  capresources: CapResource[]; // For table

  constructor(
    private crs: CapabilityResourceService,
    private cs: CapabilityService,
    private ns: NavbarService,
    private rc: ResourceComponent
  ) { }

  ngOnInit(): void {
    this.getCapability();
    this.getAllCapResources();
  }

  // To GET the cap
  private getCapability(): void {
    var envId = this.ns.getEnvironmentCookie(), capId = this.ns.getCapabilityCookie();
    this.cs.getCapability(envId, capId).subscribe(response => this.capability = response);
  }

  // To GET all cap res links by cap id
  private getAllCapResources(): void {
    var capabilityID = this.ns.getCapabilityCookie();
    this.crs.getAllCapResourcesByCapability(capabilityID).subscribe(response => this.capresources = response);
  }

  /////// To show the child comps ///////
  // capability-resource-add.component
  showCapResAdd: boolean = false;

  // capability-resource-edit.component
  showCapResEdit: boolean = false;

  // capability-resource-delete.component
  showCapResDelete: boolean = false;
  capresCurrentValues: CapResource;

  show(component: string, capresource?: CapResource): void {
    switch (component) {
      case 'capability-resource-add':
        this.hideAll();
        // Show
        this.showCapResAdd = true;
        break;
      case 'capability-resource-edit':
        this.showCapResAdd = false;
        this.showCapResDelete = false;
        // Show
        this.capresCurrentValues = capresource;
        this.showCapResEdit = !this.showCapResEdit;
        break;
      case 'capability-resource-delete':
        this.showCapResAdd = false;
        this.showCapResEdit = false;
        // Show
        this.capresCurrentValues = capresource;
        this.showCapResDelete = !this.showCapResDelete;
        break;
      default:
        break;
    }
  }

  hideAll(): void {
    this.showCapResAdd = false;
    this.showCapResEdit = false;
    this.showCapResDelete = false;
  }

}

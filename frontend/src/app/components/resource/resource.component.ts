import { Component, OnInit } from '@angular/core';
import { CapResource } from 'src/app/classes/capabillity-resource/capresource';
import { Resource } from 'src/app/classes/resource/resource';
import { CapabilityResourceService } from 'src/app/services/capability-resource/capability-resource.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { ResourceService } from 'src/app/services/resource/resource.service';

@Component({
  selector: 'app-resource',
  templateUrl: './resource.component.html',
  styleUrls: ['./resource.component.css']
})
export class ResourceComponent implements OnInit {

  resources: Resource[];
  capresources: CapResource[];

  constructor(
    private rs: ResourceService,
    private crs: CapabilityResourceService,
    private ns: NavbarService
  ) { }

  ngOnInit(): void {
    this.getAllResources();
  }

  private getAllResources(): void {
    var environmentId = this.ns.getEnvironmentCookie();

    this.rs.getAllResources(environmentId).subscribe(response => this.resources = response);
  }

  showLinkedCapabilities(resourceID: string): void {
    this.crs.getAllCapResourcesByResourceId(resourceID)
      .subscribe(response => {
        this.capresources = response;
        this.show('linked-caps');
      });
  }

  setCapabilityCookie(capabilityId: string): void {
    this.ns.setCapabilityCookie(capabilityId);
  }

  /////// To show the child comps ///////
  resourceCurrentValues: Resource;

  // resource-add.component
  showResourceAdd: boolean = false;

  // resource-edit.component
  showResourceEdit: boolean = false;

  // resource-delete.component
  showResourceDelete: boolean = false;

  // Linked caps right panel
  showLinkedCaps: boolean = false;

  show(component: string, resource?: Resource): void {
    switch (component) {
      case 'resource-add':
        this.hideAll();
        // Show
        this.showResourceAdd = true;
        break;
      case 'resource-edit':
        // Hide
        this.showResourceAdd = false;
        this.showResourceDelete = false;
        this.showLinkedCaps = false;
        // Show
        this.resourceCurrentValues = resource;
        this.showResourceEdit = !this.showResourceEdit;
        break;
      case 'resource-delete':
        // Hide
        this.showResourceAdd = false;
        this.showResourceEdit = false;
        this.showLinkedCaps = false;
        // Show
        this.resourceCurrentValues = resource;
        this.showResourceDelete = !this.showResourceDelete;
        break;
      case 'linked-caps':
        this.hideAll();
        // Show
        this.showLinkedCaps = true;
        break;
      default:
        break;
    }
  }

  hideAll(): void {
    this.showResourceAdd = false;
    this.showResourceEdit = false;
    this.showResourceDelete = false;
    this.showLinkedCaps = false;
  }

}

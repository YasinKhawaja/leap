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

  resources: Resource[]; // For table

  constructor(private rs: ResourceService, private crs: CapabilityResourceService, private ns: NavbarService) { }

  ngOnInit(): void {
    this.getAllResources();
  }

  // To GET all res
  private getAllResources(): void {
    this.rs.getAllResources().subscribe(res => this.resources = res, err => console.error(err));
  }

  // To GET all cap res links by res id
  capresources: CapResource[];

  showLinkedCapabilities(resourceID: string): void {
    this.crs.getAllCapResourcesByResourceId(resourceID)
      .subscribe(response => {
        this.capresources = response;
        this.show('linked-caps');
      });
  }

  // To set the cap cookie when clicked on a cap
  setCapabilityCookie(capabilityID: string): void {
    this.ns.setCapabilityCookie(capabilityID);
  }

  /////// To show the child comps ///////
  resCurrentValues: Resource;

  // resource-add.component
  showResAdd: boolean = false;

  // resource-edit.component
  showResEdit: boolean = false;

  // resource-delete.component
  showResDelete: boolean = false;

  // Linked caps right panel
  showLinkedCaps: boolean = false;

  show(component: string, resource?: Resource): void {
    switch (component) {
      case 'resource-add':
        this.hideAll();
        // Show
        this.showResAdd = true;
        break;
      case 'resource-edit':
        // Hide
        this.showResAdd = false;
        this.showResDelete = false;
        this.showLinkedCaps = false;
        // Show
        this.resCurrentValues = resource;
        this.showResEdit = !this.showResEdit;
        break;
      case 'resource-delete':
        // Hide
        this.showResAdd = false;
        this.showResEdit = false;
        this.showLinkedCaps = false;
        // Show
        this.resCurrentValues = resource;
        this.showResDelete = !this.showResDelete;
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
    this.showResAdd = false;
    this.showResEdit = false;
    this.showResDelete = false;
    this.showLinkedCaps = false;
  }

}

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

  constructor(private rs: ResourceService, private crs: CapabilityResourceService, private ns: NavbarService) { }

  ngOnInit(): void {
    this.getAllResources();
  }

  // To GET all res
  private getAllResources(): void {
    this.rs.getAllResources().subscribe(res => this.resources = res, err => console.error(err));
  }

  // To DELETE a res
  deleteResource(resId): void {
    this.rs.deleteResource(resId).subscribe(resp => this.ngOnInit(), err => console.error(err));
  }

  // To GET all cap res links by res id
  capresources: CapResource[];

  showLinkedCapabilities(resourceID: string): void {
    this.crs.getAllCapResourcesByResourceId(resourceID)
      .subscribe(resp => {
        this.capresources = resp;
        this.show('linked-caps');
      });
  }

  setCapabilityCookie(capabilityID: string): void {
    this.ns.setCapabilityCookie(capabilityID);
  }

  // To show the child comps
  // resource-add-component
  showResAdd: boolean = false;

  // resource-edit-component
  showResEdit: boolean = false;
  resCurrentValues: Resource;

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
        this.showLinkedCaps = false;
        // Show
        this.resCurrentValues = resource;
        this.showResEdit = !this.showResEdit;
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
    this.showLinkedCaps = false;
  }

}

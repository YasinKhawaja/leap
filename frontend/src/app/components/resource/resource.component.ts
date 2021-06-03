import { Component, OnInit, ViewChild } from '@angular/core';
import { Resource } from 'src/app/classes/resource/resource';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { ResourceService } from 'src/app/services/resource/resource.service';
import { ResourceEditComponent } from '../resource-edit/resource-edit.component';

@Component({
  selector: 'app-resource',
  templateUrl: './resource.component.html',
  styleUrls: ['./resource.component.css']
})
export class ResourceComponent implements OnInit {

  resources: Resource[];

  constructor(private rs: ResourceService, private ns: NavbarService) { }

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

  // To show the child comps
  showResAdd: boolean = false; // resource-add-component

  // resource-edit-component
  showResEdit: boolean = false;
  resCurrentValues: Resource;

  show(component: string, resource?: Resource): void {
    switch (component) {
      case 'resource-add':
        // Hide
        this.showResEdit = false;
        // Show
        this.showResAdd = true;
        break;
      case 'resource-edit':
        // Hide
        this.showResAdd = false;
        // Show
        this.resCurrentValues = resource;
        this.showResEdit = !this.showResEdit;
        break;
      default:
        break;
    }
  }

  hideAll(): void {
    this.showResAdd = false;
    this.showResEdit = false;
  }

}

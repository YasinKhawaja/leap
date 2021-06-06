import { Component, Input, OnInit } from '@angular/core';
import { Resource } from 'src/app/classes/resource/resource';
import { CapabilityResourceService } from 'src/app/services/capability-resource/capability-resource.service';
import { ResourceService } from 'src/app/services/resource/resource.service';
import { CapabilityResourceComponent } from '../capability-resource/capability-resource.component';
import { ResourceComponent } from '../resource/resource.component';

@Component({
  selector: 'app-resource-delete',
  templateUrl: './resource-delete.component.html',
  styleUrls: ['./resource-delete.component.css']
})
export class ResourceDeleteComponent implements OnInit {

  @Input() resCurrentValues: Resource; // From resource.component

  constructor(private rs: ResourceService, private rc: ResourceComponent) { }

  ngOnInit(): void { }

  // To hide the comp
  hide(): void {
    this.rc.hideAll();
  }

  // To DELETE the resource
  deleteResource(): void {
    this.rs.deleteResource(this.resCurrentValues.id)
      .subscribe(
        response => {
          this.rc.ngOnInit();
          this.rc.hideAll();
        }
      );
  }

}

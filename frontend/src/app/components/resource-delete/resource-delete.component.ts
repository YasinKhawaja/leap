import { Component, Input, OnInit } from '@angular/core';
import { Resource } from 'src/app/classes/resource/resource';
import { ResourceService } from 'src/app/services/resource/resource.service';
import { ResourceComponent } from '../resource/resource.component';

@Component({
  selector: 'app-resource-delete',
  templateUrl: './resource-delete.component.html',
  styleUrls: ['./resource-delete.component.css']
})
export class ResourceDeleteComponent implements OnInit {

  @Input() resourceCurrentValues: Resource;

  constructor(private rs: ResourceService, private rc: ResourceComponent) { }

  ngOnInit(): void { }

  hide(): void {
    this.rc.hideAll();
  }

  deleteResource(): void {
    this.rs.deleteResource(this.resourceCurrentValues.id)
      .subscribe(
        response => {
          this.rc.ngOnInit();
          this.rc.hideAll();
        }
      );
  }

}

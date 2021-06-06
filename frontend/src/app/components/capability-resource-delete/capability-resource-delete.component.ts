import { Component, Input, OnInit } from '@angular/core';
import { CapResource } from 'src/app/classes/capabillity-resource/capresource';
import { CapabilityResourceService } from 'src/app/services/capability-resource/capability-resource.service';
import { CapabilityResourceComponent } from '../capability-resource/capability-resource.component';

@Component({
  selector: 'app-capability-resource-delete',
  templateUrl: './capability-resource-delete.component.html',
  styleUrls: ['./capability-resource-delete.component.css']
})
export class CapabilityResourceDeleteComponent implements OnInit {

  @Input() capresCurrentValues: CapResource; // From capability-resource.component

  constructor(private crs: CapabilityResourceService, private crc: CapabilityResourceComponent) { }

  ngOnInit(): void { }

  // To hide the comp
  hide(): void {
    this.crc.hideAll();
  }

  // To DELETE the cap res link
  deleteCapResource(): void {
    this.crs.deleteCapResource(this.capresCurrentValues.id)
      .subscribe(
        response => {
          this.crc.ngOnInit();
          this.crc.hideAll();
        }
      );
  }

}

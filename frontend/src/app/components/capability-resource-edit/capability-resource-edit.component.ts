import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CapResource } from 'src/app/classes/capabillity-resource/capresource';
import { CapabilityResourceService } from 'src/app/services/capability-resource/capability-resource.service';
import Swal from 'sweetalert2';
import { CapabilityResourceComponent } from '../capability-resource/capability-resource.component';

@Component({
  selector: 'app-capability-resource-edit',
  templateUrl: './capability-resource-edit.component.html',
  styleUrls: ['./capability-resource-edit.component.css']
})
export class CapabilityResourceEditComponent implements OnInit {

  capresEditForm: FormGroup;
  @Input() capresCurrentValues: CapResource;

  constructor(
    private crs: CapabilityResourceService,
    private fb: FormBuilder,
    private crc: CapabilityResourceComponent
  ) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  private initializeForm() {
    this.capresEditForm = this.fb.group({
      criticality: ['']
    });
  }

  get nor() {
    return this.capresEditForm.get('numberOfResources');
  }

  onSubmit() {
    this.crs.updateCapResource(this.capresCurrentValues.id, this.nor.value)
      .subscribe(
        response => {
          this.crc.ngOnInit();
          this.crc.hideAll();
        },
        error => Swal.fire('Error', error.error.message, 'error')
      );
  }

  hide(): void {
    this.crc.hideAll();
  }

}

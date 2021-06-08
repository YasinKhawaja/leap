import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Resource } from 'src/app/classes/resource/resource';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { ResourceService } from 'src/app/services/resource/resource.service';
import Swal from 'sweetalert2';
import { ResourceComponent } from '../resource/resource.component';

@Component({
  selector: 'app-resource-edit',
  templateUrl: './resource-edit.component.html',
  styleUrls: ['./resource-edit.component.css']
})
export class ResourceEditComponent implements OnInit {

  resourceEditForm: FormGroup;
  @Input() resourceCurrentValues: Resource;

  constructor(
    private rs: ResourceService,
    private fb: FormBuilder,
    private rc: ResourceComponent
  ) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm() {
    this.resourceEditForm = this.fb.group({
      name: [this.resourceCurrentValues.name, [
        Validators.required,
        Validators.pattern('[a-zA-Z]+')]
      ],
      description: this.resourceCurrentValues.description,
      fullTimeEquivalentYearlyValue: [this.resourceCurrentValues.fullTimeEquivalentYearlyValue, Validators.pattern('[0-9]+')]
    });
  }

  get name() {
    return this.resourceEditForm.get('name');
  }

  get desc() {
    return this.resourceEditForm.get('description');
  }

  get fteyv() {
    return this.resourceEditForm.get('fullTimeEquivalentYearlyValue');
  }

  onSubmit() {
    var resourceIdToUpdate = this.resourceCurrentValues.id;
    var resourceNewValues = new Resource(this.name.value, this.desc.value, this.fteyv.value);

    this.rs.updateResource(resourceIdToUpdate, resourceNewValues)
      .subscribe(
        response => {
          this.rc.ngOnInit();
          this.rc.hideAll();
        },
        err => Swal.fire('Error', err.error.message, 'error')
      );
  }

  hide(): void {
    this.rc.hideAll();
  }

}

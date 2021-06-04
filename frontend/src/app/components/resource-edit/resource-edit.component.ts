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

  // Form
  resEditForm: FormGroup;
  @Input() resCurrentValues: Resource;

  constructor(private rs: ResourceService, private ns: NavbarService, private fb: FormBuilder, private rc: ResourceComponent) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  // To initialize the form in HTML with the res current values
  initializeForm() {
    this.resEditForm = this.fb.group({
      name: [this.resCurrentValues.name, [
        Validators.required,
        Validators.pattern('[a-zA-Z]+')]
      ],
      description: this.resCurrentValues.description,
      fullTimeEquivalentYearlyValue: this.resCurrentValues.fullTimeEquivalentYearlyValue
    });
  }

  // Form GETTERS
  get name() {
    return this.resEditForm.get('name');
  }

  get desc() {
    return this.resEditForm.get('description');
  }

  get fteyv() {
    return this.resEditForm.get('fullTimeEquivalentYearlyValue');
  }

  // Form submit logic
  onSubmit() {
    var resIdToUpdate = this.resCurrentValues.id;
    var resNewValues = new Resource(this.name.value, this.desc.value, this.fteyv.value);

    this.rs.updateResource(resIdToUpdate, resNewValues)
      .subscribe(
        resp => {
          this.rc.ngOnInit();
          this.rc.hideAll();
        },
        err => Swal.fire('Error', err.error.message, 'error')
      );
  }

}

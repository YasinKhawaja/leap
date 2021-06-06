import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Resource } from 'src/app/classes/resource/resource';
import { ResourceService } from 'src/app/services/resource/resource.service';
import Swal from 'sweetalert2';
import { ResourceComponent } from '../resource/resource.component';

@Component({
  selector: 'app-resource-add',
  templateUrl: './resource-add.component.html',
  styleUrls: ['./resource-add.component.css']
})
export class ResourceAddComponent implements OnInit {

  // Form
  resAddForm: FormGroup;

  constructor(private rs: ResourceService, private fb: FormBuilder, private rc: ResourceComponent) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  // To initialize the form in HTML
  private initializeForm() {
    this.resAddForm = this.fb.group({
      name: ['', [
        Validators.required,
        Validators.pattern('[a-zA-Z]+')]
      ],
      description: '',
      fullTimeEquivalentYearlyValue: ['', Validators.pattern('[0-9]+')]
    });
  }

  // Form GETTERS
  get name() {
    return this.resAddForm.get('name');
  }

  get desc() {
    return this.resAddForm.get('description');
  }

  get fteyv() {
    return this.resAddForm.get('fullTimeEquivalentYearlyValue');
  }

  // Form submit logic
  onSubmit() {
    var resToCreate = new Resource(this.name.value, this.desc.value, this.fteyv.value);

    this.rs.createResource(resToCreate)
      .subscribe(
        response => {
          this.rc.ngOnInit();
          this.resAddForm.reset();
        },
        err => Swal.fire('Error', err.error.message, 'error')
      );
  }

  // To hide the form
  hide(): void {
    this.rc.hideAll();
  }

}

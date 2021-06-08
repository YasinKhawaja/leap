import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Resource } from 'src/app/classes/resource/resource';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { ResourceService } from 'src/app/services/resource/resource.service';
import { environment } from 'src/environments/environment';
import Swal from 'sweetalert2';
import { ResourceComponent } from '../resource/resource.component';

@Component({
  selector: 'app-resource-add',
  templateUrl: './resource-add.component.html',
  styleUrls: ['./resource-add.component.css']
})
export class ResourceAddComponent implements OnInit {

  resourceAddForm: FormGroup;

  constructor(
    private rs: ResourceService,
    private ns: NavbarService,
    private fb: FormBuilder,
    private rc: ResourceComponent
  ) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  private initializeForm() {
    this.resourceAddForm = this.fb.group({
      name: ['', [
        Validators.required,
        Validators.pattern('[a-zA-Z]+')]
      ],
      description: '',
      fullTimeEquivalentYearlyValue: ['', Validators.pattern('[0-9]+')]
    });
  }

  get name() {
    return this.resourceAddForm.get('name');
  }

  get desc() {
    return this.resourceAddForm.get('description');
  }

  get fteyv() {
    return this.resourceAddForm.get('fullTimeEquivalentYearlyValue');
  }

  onSubmit() {
    var environmentId = this.ns.getEnvironmentCookie();
    var resourceToCreate = new Resource(this.name.value, this.desc.value, this.fteyv.value);

    this.rs.createResource(environmentId, resourceToCreate)
      .subscribe(
        response => {
          this.rc.ngOnInit();
          this.resourceAddForm.reset();
        },
        err => Swal.fire('Error', err.error.message, 'error')
      );
  }

  hide(): void {
    this.rc.hideAll();
  }

}

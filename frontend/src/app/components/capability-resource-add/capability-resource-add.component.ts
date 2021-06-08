import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Capability } from 'src/app/classes/capability/capability';
import { Resource } from 'src/app/classes/resource/resource';
import { CapabilityResourceService } from 'src/app/services/capability-resource/capability-resource.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { ResourceService } from 'src/app/services/resource/resource.service';
import Swal from 'sweetalert2';
import { CapabilityResourceComponent } from '../capability-resource/capability-resource.component';

@Component({
  selector: 'app-capability-resource-add',
  templateUrl: './capability-resource-add.component.html',
  styleUrls: ['./capability-resource-add.component.css']
})
export class CapabilityResourceAddComponent implements OnInit {

  capresAddForm: FormGroup; // Form
  @Input() capability: Capability; // Cap from capability-resource.component
  resources: Resource[]; // For 'Resource' form dropdown

  constructor(
    private fb: FormBuilder,
    private rs: ResourceService,
    private ns: NavbarService,
    private crs: CapabilityResourceService,
    private crc: CapabilityResourceComponent
  ) {
    this.getAllResources().subscribe(response => { this.resources = response });
  }

  ngOnInit(): void {
    this.initializeForm();
  }

  // To GET all res
  private getAllResources(): Observable<Resource[]> {
    var environmentId = this.ns.getEnvironmentCookie();

    return this.rs.getAllResources(environmentId);
  }

  // To initialize the form in HTML
  private initializeForm() {
    this.capresAddForm = this.fb.group({
      resource: ['', Validators.required],
      numberOfResources: ['', Validators.pattern('[0-9]+')]
    });
  }

  // Form GETTERS
  get res() {
    return this.capresAddForm.get('resource');
  }

  get nor() {
    return this.capresAddForm.get('numberOfResources');
  }

  resource: Resource;
  getResourceFromDropdown(resource: Resource): void {
    this.resource = resource;
  }

  // Form submit logic
  onSubmit() {
    var capId = this.capability.id, resId = this.resource.id;

    this.crs.createCapResource(capId, resId, this.nor.value)
      .subscribe(
        response => {
          this.crc.ngOnInit();
        },
        error => Swal.fire('Error', error.error.message, 'error')
      );
  }

  // To hide the form
  hide(): void {
    this.crc.hideAll();
  }

}

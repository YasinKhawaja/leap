import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import Swal from 'sweetalert2';
import { Capability } from '../../classes/capability/capability';
import { CapabilityService } from '../../services/capability/capability.service';

enum PaceOfChange {
  NONE = 'NONE',
  STANDARD = 'STANDARD',
  DIFFERENTIATION = 'DIFFERENTIATION',
  INNOVATIVE = 'INNOVATIVE'
}

enum TargetOperationModel {
  NONE = 'NONE',
  COORDINATION = 'COORDINATION',
  DIVERSIFICATION = 'DIVERSIFICATION',
  REPLICATION = 'REPLICATION',
  UNIFICATION = 'UNIFICATION'
}

@Component({
  selector: 'app-capability-edit',
  templateUrl: './capability-edit.component.html',
  styleUrls: ['./capability-edit.component.css']
})
export class CapabilityEditComponent implements OnInit {

  // Enums ^
  ePoc = PaceOfChange;
  eTom = TargetOperationModel;
  // Form
  capEditForm: FormGroup;
  capCurrentValues: Capability;

  constructor(private cs: CapabilityService, private ns: NavbarService, private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.getCapabilityCurrentValues()
      .subscribe(
        res => {
          this.capCurrentValues = res;
          this.initializeForm();
        }
      );
  }

  // To GET the cap current values to initialize the form with
  private getCapabilityCurrentValues(): Observable<Capability> {
    var envId = this.ns.getEnvironmentCookie();
    var capId = this.ns.getCapabilityCookie();

    return this.cs.getCapability(envId, capId);
  }

  // To initialize the form in HTML with the cap current values ^
  private initializeForm() {
    this.capEditForm = this.fb.group({
      name: [this.capCurrentValues.name, [
        Validators.required,
        Validators.pattern('[a-zA-Z]+')]
      ],
      paceOfChange: [this.capCurrentValues.paceOfChange, Validators.required],
      targetOperationModel: [this.capCurrentValues.targetOperationModel, Validators.required],
      resourcesQuality: [this.capCurrentValues.resourcesQuality, [Validators.pattern('[1-5]')]]
    });
  }

  // Form GETTERS
  get name() {
    return this.capEditForm.get('name');
  }

  get poc() {
    return this.capEditForm.get('paceOfChange');
  }

  get tom() {
    return this.capEditForm.get('targetOperationModel');
  }

  get rq() {
    return this.capEditForm.get('resourcesQuality');
  }

  // When clicked on "EDIT" and submitting the form
  onSubmit() {
    var envId = this.ns.getEnvironmentCookie();
    var capIdToUpdate = this.ns.getCapabilityCookie();

    var newCapValues = new Capability(
      this.name.value,
      this.poc.value,
      this.tom.value,
      this.rq.value
    );

    this.cs.updateCapabilityInEnvironment(envId, capIdToUpdate, newCapValues)
      .subscribe(
        res => {
          Swal.fire('Edited', `Capability <strong>${this.capCurrentValues.name}</strong> edited.`, 'success');
          this.ngOnInit();
        },
        err => console.error(err)
      );
  }

  refer() {
    var capabilityId = this.ns.getCapabilityCookie();
    this.router.navigate([`capability-application/${capabilityId}`])
  }

}

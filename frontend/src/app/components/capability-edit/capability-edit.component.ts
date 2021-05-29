import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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

  constructor(private cs: CapabilityService, private ns: NavbarService, private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  // To initialize the form in HTML
  private initializeForm() {
    this.capEditForm = this.fb.group({
      name: [
        '', [
          Validators.required,
          Validators.pattern('[a-zA-Z]+')
        ]
      ],
      paceOfChange: ['', Validators.required],
      targetOperationModel: ['', Validators.required],
      resourcesQuality: ['', [Validators.pattern('[1-5]')]]
    });
  }

  // Form GETTERS
  get name() {
    return this.capEditForm.get('name');
  }

  get paceOfChange() {
    return this.capEditForm.get('paceOfChange');
  }

  get targetOperationModel() {
    return this.capEditForm.get('targetOperationModel');
  }

  get resourcesQuality() {
    return this.capEditForm.get('resourcesQuality');
  }

  refer() {
    var capabilityId = this.router.url.split('/')[4];
    this.router.navigate([`capability-application/${capabilityId}`])
  }

  onSubmit() {
    var envId = this.ns.getEnvironment();
    var capIdToUpdate = this.router.url.split('/')[2];

    var newCapValues = new Capability(
      this.name.value,
      this.paceOfChange.value,
      this.targetOperationModel.value,
      this.resourcesQuality.value
    );

    this.cs.updateCapabilityInEnvironment(envId, capIdToUpdate, newCapValues)
      .subscribe(
        res => {
          console.log(res);
          Swal.fire('Updated', `Capability updated.`, 'success');
          this.capEditForm.reset();
        },
        err => console.error(err)
      );
  }

}

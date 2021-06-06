import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Capability } from 'src/app/classes/capability/capability';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import Swal from 'sweetalert2';
import { CapabilityService } from "../../services/capability/capability.service";
import { CapabilityComponent } from '../capability/capability.component';

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
  selector: 'app-capability-add',
  templateUrl: './capability-add.component.html',
  styleUrls: ['./capability-add.component.css']
})
export class CapabilityAddComponent implements OnInit {

  // Cap from "capability" comp
  @Input() cap: Capability;
  // Enums ^
  ePoc = PaceOfChange;
  eTom = TargetOperationModel;
  // Form
  capAddForm: FormGroup;

  constructor(private cs: CapabilityService, private ns: NavbarService, private cc: CapabilityComponent, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  // To initialize the form in HTML
  private initializeForm() {
    this.capAddForm = this.fb.group({
      name: ['', [
        Validators.required,
        Validators.pattern('[a-zA-Z]+')]
      ],
      paceOfChange: ['', Validators.required],
      targetOperationModel: ['', Validators.required],
      resourcesQuality: ['', [Validators.pattern('[1-5]')]]
    });
  }

  // Form GETTERS
  get name() {
    return this.capAddForm.get('name');
  }

  get paceOfChange() {
    return this.capAddForm.get('paceOfChange');
  }

  get targetOperationModel() {
    return this.capAddForm.get('targetOperationModel');
  }

  get resourcesQuality() {
    return this.capAddForm.get('resourcesQuality');
  }

  // Form submit logic
  onSubmit() {
    var envId = this.ns.getEnvironmentCookie();

    var parentCapId = '';
    if (this.cap == null) {
      parentCapId = '0'; // = no parent cap
    } else {
      parentCapId = this.cap.id;
    }

    var capToCreate = new Capability(
      this.name.value,
      this.paceOfChange.value,
      this.targetOperationModel.value,
      this.resourcesQuality.value
    );

    this.cs.createCapability(envId, parentCapId, capToCreate)
      .subscribe(
        res => {
          this.capAddForm.reset();
          this.cc.ngOnInit();
        },
        err => Swal.fire('Error', err.error.message, 'error')
      );
  }

}

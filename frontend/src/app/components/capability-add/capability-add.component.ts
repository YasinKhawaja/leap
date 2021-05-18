import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Capability } from 'src/app/classes/capability/capability';
import Swal from 'sweetalert2';
import { CapabilityService } from "../../services/capability/capability.service";

enum PaceOfChange {
  NONE = 'NONE',
  STANDARD = 'STANDARD',
  DIFFERENTIATION = 'DIFFERENTIATION',
  INNOVATIVE = 'INNOVATIVE'
}

enum Tom {
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

  // Enums ^
  ePaceOfChange = PaceOfChange;
  eTom = Tom;
  // Form
  capAddForm: FormGroup;

  constructor(private cs: CapabilityService, private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  // To initialize the form in HTML
  private initializeForm() {
    this.capAddForm = this.fb.group({
      name: ['', [Validators.required, Validators.pattern('[a-zA-Z]+')]],
      paceOfChange: ['', Validators.required],
      targetOperationModel: ['', Validators.required],
      resourcesQuality: ['', [Validators.required, Validators.pattern('[1-5]')]]
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
    var envId = this.router.url.split('/')[2];
    var parentCapId = '0';

    var capToCreate = new Capability(
      this.name.value,
      this.paceOfChange.value,
      this.targetOperationModel.value,
      this.resourcesQuality.value
    );

    this.cs.createCapability(envId, parentCapId, capToCreate)
      .subscribe(
        response => console.log(response),
        error => Swal.fire('Error', error.error.message, 'error')
      );

    this.router.navigate([`environments/${envId}/capabilities`]);
  }

}
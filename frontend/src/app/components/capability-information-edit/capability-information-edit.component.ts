import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { CapabilityInformation } from 'src/app/classes/capability-information/capability-information';
import { Information } from 'src/app/classes/information/information';
import { CapabilityInformationService } from 'src/app/services/capability-information/capability-information.service';
import { InformationService } from 'src/app/services/information/information.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import Swal from 'sweetalert2';
import { CapabilityInformationComponent } from '../capability-information/capability-information.component';

enum Criticality {
  LOW = 'Low',
  MEDIUM = 'Medium',
  HIGH = 'High'
}

@Component({
  selector: 'app-capability-information-edit',
  templateUrl: './capability-information-edit.component.html',
  styleUrls: ['./capability-information-edit.component.css']
})
export class CapabilityInformationEditComponent implements OnInit {

  capinfoEditForm: FormGroup
  @Input() capinfoCurrentValues: CapabilityInformation
  eCrit = Criticality

  constructor(private cis: CapabilityInformationService, private fb: FormBuilder, private cic: CapabilityInformationComponent) {
  }

  ngOnInit(): void {
    this.capinfoEditForm = this.fb.group({
      criticality: ['']
    });
  }

  get crit() {
    return this.capinfoEditForm.get('criticality')
  }

  information: Information
  getInformationFromDropdown(information: Information) {
    this.information = information
  }

  onSubmit() {
    this.cis.updateCapInfo(this.capinfoCurrentValues.id, this.crit.value)
      .subscribe(
        () => {
          this.cic.ngOnInit()
          this.cic.hideAll()
        },
        () => Swal.fire('Error', `Failed to add capability information link`, `error`)
      )
  }

  hide() {
    this.cic.hideAll()
  }
}

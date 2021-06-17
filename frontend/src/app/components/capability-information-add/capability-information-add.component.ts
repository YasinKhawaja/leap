import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Capability } from 'src/app/classes/capability/capability';
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
  selector: 'app-capability-information-add',
  templateUrl: './capability-information-add.component.html',
  styleUrls: ['./capability-information-add.component.css']
})
export class CapabilityInformationAddComponent implements OnInit {

  capinfoAddForm: FormGroup
  @Input() capability: Capability
  informationList: Information[]
  eCrit = Criticality

  constructor(private fb: FormBuilder, private is: InformationService, private ns: NavbarService, private cis: CapabilityInformationService, private cic: CapabilityInformationComponent) {
    this.getInformationList()
      .subscribe(
        (data) => {
          this.informationList = data
        }
      )
  }

  ngOnInit(): void {
    this.capinfoAddForm = this.fb.group({
      information: ['', Validators.required],
      criticality: ['']
    })
  }

  private getInformationList(): Observable<Information[]> {
    var environmentId = this.ns.getEnvironmentCookie();

    return this.is.getInformationList(environmentId);
  }

  get info() {
    return this.capinfoAddForm.get('information')
  }

  get crit() {
    return this.capinfoAddForm.get('criticality')
  }

  information: Information
  getInformationFromDropdown(information: Information) {
    this.information = information
  }

  onSubmit() {
    var capid = this.capability.id
    var infoid = this.information.id;

    this.cis.addCapInfo(capid, infoid, this.crit.value)
      .subscribe(
        () => {
          this.cic.ngOnInit();
        },
        () => {
          Swal.fire('Error', `Failed to add capability-information link`, 'error')
        }
      )
  }

  hide() {
    this.cic.hideAll();
  }
}

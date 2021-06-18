import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Information } from 'src/app/classes/information/information';
import { InformationService } from 'src/app/services/information/information.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import Swal from 'sweetalert2';
import { InformationComponent } from '../information/information.component';

@Component({
  selector: 'app-information-add',
  templateUrl: './information-add.component.html',
  styleUrls: ['./information-add.component.css']
})
export class InformationAddComponent implements OnInit {

  informationAddForm: FormGroup

  constructor(private is: InformationService, private ns: NavbarService, private fb: FormBuilder, private ic: InformationComponent) { }

  ngOnInit(): void {
    this.informationAddForm = this.fb.group({
      name: ['', [
        Validators.required,
        Validators.pattern('[a-zA-Z]+')]
      ],
      description: '',
      fullTimeEquivalentYearlyValue: ['', Validators.pattern('[0-9]+')]
    });
  }
  get name() {
    return this.informationAddForm.get('name');
  }

  get desc() {
    return this.informationAddForm.get('description');
  }

  onSubmit() {
    var environmentId = this.ns.getEnvironmentCookie();
    var newInformation = new Information(this.name.value, this.desc.value);

    this.is.addInformation(environmentId, newInformation)
      .subscribe(
        () => {
          this.ic.ngOnInit();
          this.informationAddForm.reset();
        },
        () => Swal.fire('Error', `Failed to add information: ${newInformation.name}`, 'error')
      );
  }

  hide(): void {
    this.ic.hideAll();
  }
}

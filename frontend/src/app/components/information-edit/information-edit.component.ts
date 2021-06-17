import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Information } from 'src/app/classes/information/information';
import { InformationService } from 'src/app/services/information/information.service';
import Swal from 'sweetalert2';
import { InformationComponent } from '../information/information.component';

@Component({
  selector: 'app-information-edit',
  templateUrl: './information-edit.component.html',
  styleUrls: ['./information-edit.component.css']
})
export class InformationEditComponent implements OnInit {

  informationEditForm: FormGroup;
  @Input() informationCurrentValues: Information

  constructor(private is: InformationService, private fb: FormBuilder, private ic: InformationComponent) { }

  ngOnInit(): void {
    this.informationEditForm = this.fb.group({
      name: [this.informationCurrentValues.name, [
        Validators.required,
        Validators.pattern('[a-zA-Z]+')]
      ],
      description: this.informationCurrentValues.description
    });
  }
  get name() {
    return this.informationEditForm.get('name');
  }

  get desc() {
    return this.informationEditForm.get('description');
  }

  onSubmit() {
    var informationid = this.informationCurrentValues.id;
    var newInformation = new Information(this.name.value, this.desc.value);

    this.is.updateInformation(informationid, newInformation)
      .subscribe(
        response => {
          this.ic.ngOnInit();
          this.ic.hideAll();
        },
        () => Swal.fire('Error', `Failed to update Information ${this.informationCurrentValues.name}`, 'error')
      );
  }

  hide() {
    this.ic.hideAll();
  }

}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Businessprocess } from 'src/app/classes/businessprocess/businessprocess';
import { BusinessprocessService } from 'src/app/services/businessprocess/businessprocess.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import Swal from 'sweetalert2';
import { BusinessprocessComponent } from '../businessprocess/businessprocess.component';

@Component({
  selector: 'app-businessprocess-add',
  templateUrl: './businessprocess-add.component.html',
  styleUrls: ['./businessprocess-add.component.css']
})
export class BusinessprocessAddComponent implements OnInit {

   // Form
   businessprocess: FormGroup;

   ngOnInit(): void {
    this.initializeForm();
  }

  private initializeForm() {
    this.businessprocess = this.fb.group({
      name: ['', Validators.required],
    description: [' ', Validators.nullValidator]
    });
  }

  constructor(private fb: FormBuilder, private bps: BusinessprocessService, private ns: NavbarService , private bpc : BusinessprocessComponent) { }

  onSubmit(){
    var environmentid = this.ns.getEnvironmentCookie();

    var newBusinessProcess = new Businessprocess(
      this.businessprocess.value.name,
      this.businessprocess.value.description
    );

    this.bps.addBusinessProcess(environmentid, newBusinessProcess)
    .subscribe(
      () => {
        this.bpc.ngOnInit();
      },
      () => {
        Swal.fire('Error', `Failed to add business process`, 'error')
      }
    )
  }

 

  hide(): void {
    this.bpc.hideAll();
  }
}

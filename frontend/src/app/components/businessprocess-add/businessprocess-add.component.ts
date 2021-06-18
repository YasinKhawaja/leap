import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Businessprocess } from 'src/app/classes/businessprocess/businessprocess';
import { BusinessprocessService } from 'src/app/services/businessprocess/businessprocess.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { BusinessprocessComponent } from '../businessprocess/businessprocess.component';

@Component({
  selector: 'app-businessprocess-add',
  templateUrl: './businessprocess-add.component.html',
  styleUrls: ['./businessprocess-add.component.css']
})
export class BusinessprocessAddComponent {

  businessprocess = this.fb.group({
    name: ['', Validators.required],
    description: [' ', Validators.nullValidator]
  });

  constructor(private fb: FormBuilder, private bps: BusinessprocessService, private ns: NavbarService , private bpc : BusinessprocessComponent) { }

  onSubmit(){
    var environmentid = this.ns.getEnvironmentCookie();

    var newBusinessProcess = new Businessprocess(
      this.businessprocess.value.name,
      this.businessprocess.value.description
    );

    this.bps.addBusinessProcess(environmentid, newBusinessProcess);
  }

  hide(): void {
    this.bpc.hideAll();
  }
}

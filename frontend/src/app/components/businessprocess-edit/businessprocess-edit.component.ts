import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Businessprocess } from 'src/app/classes/businessprocess/businessprocess';
import { BusinessprocessService } from 'src/app/services/businessprocess/businessprocess.service';
import Swal from 'sweetalert2';
import { BusinessprocessComponent } from '../businessprocess/businessprocess.component';

@Component({
  selector: 'app-businessprocess-edit',
  templateUrl: './businessprocess-edit.component.html',
  styleUrls: ['./businessprocess-edit.component.css']
})
export class BusinessprocessEditComponent implements OnInit {

  businessprocess: FormGroup;
  @Input() processCurrentValues: Businessprocess;

  constructor( private bps: BusinessprocessService, private fb: FormBuilder, private bpc : BusinessprocessComponent) { }

  ngOnInit(): void {
    this.getBusinessProcess()
      .subscribe(
        result => {
          this.businessprocess = this.fb.group({
            name: [result.name, [Validators.required]],
            description: [result.description, [Validators.nullValidator]]
          })
        },
        () => {
          Swal.fire('Error', `Failed to load business process`, 'error');
        }
      )
  }

  private getBusinessProcess(): Observable<Businessprocess>{

    //var businessprocessid = this.router.url.split('/')[3];
    var businessprocessid = this.processCurrentValues.id;
    return this.bps.getBusinessProcess(businessprocessid);
  }

  onSubmit() {
    var businessprocessid = this.processCurrentValues.id;
    this.bps.updateBusinessProcess(businessprocessid, new Businessprocess(
      this.businessprocess.value.name,
      this.businessprocess.value.description
    ))
  }

  hide(): void {
    this.bpc.hideAll();
  }


}

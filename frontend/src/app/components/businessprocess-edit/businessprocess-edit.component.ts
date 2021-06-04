import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Businessprocess } from 'src/app/classes/businessprocess/businessprocess';
import { BusinessprocessService } from 'src/app/services/businessprocess.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-businessprocess-edit',
  templateUrl: './businessprocess-edit.component.html',
  styleUrls: ['./businessprocess-edit.component.css']
})
export class BusinessprocessEditComponent implements OnInit {

  businessprocess: FormGroup;

  constructor(private router: Router, private bps: BusinessprocessService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.getBusinessProcess()
      .subscribe(
        result => {
          this.businessprocess = this.fb.group({
            name: [result.name, [Validators.required]],
            description: [result.description, [Validators.nullValidator]]
          })
        },
        error => {
          Swal.fire('Error', error.error.message, 'error');
        }
      )
  }

  private getBusinessProcess(): Observable<Businessprocess>{
    var businessprocessid = this.router.url.split('/')[3];
    return this.bps.getBusinessProcess(businessprocessid);
  }

  onSubmit() {
    this.bps.updateBusinessProcess(this.router.url.split('/')[3], new Businessprocess(
      this.businessprocess.value.name,
      this.businessprocess.value.description
    ))
  }

}

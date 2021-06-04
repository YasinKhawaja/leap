import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { CapabilityBusinessprocess } from 'src/app/classes/capability-businessprocess/capability-businessprocess';
import { BusinessprocessService } from 'src/app/services/businessprocess/businessprocess.service';
import { CapabilityBusinessprocessService } from 'src/app/services/capability-businessprocess/capability-businessprocess.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-capability-businessprocess-add',
  templateUrl: './capability-businessprocess-add.component.html',
  styleUrls: ['./capability-businessprocess-add.component.css']
})
export class CapabilityBusinessprocessAddComponent implements OnInit {

  businessprocesses: string[]

  capabilityBusinessprocess = this.fb.group({
    businessprocess: ['', Validators.required]
  })

  constructor(private fb: FormBuilder, private cbs: CapabilityBusinessprocessService, private ns: NavbarService, private bps: BusinessprocessService) {
    this.businessprocesses = [];
  }

  ngOnInit(): void {
    var environmentid = this.ns.getEnvironment();

    this.bps.getBusinessProcesses(environmentid)
      .subscribe(
        result => {
          result.forEach(
            e => {
              this.businessprocesses.push(e.name);
          })
        },
        error => {
          Swal.fire('Error', error.error.message, 'error')
        }
      )
  }

  onSubmit() {
    var capabilityid = this.ns.getCapability();

    var newCapabilityBusinessprocess = new CapabilityBusinessprocess(
      this.capabilityBusinessprocess.value.businessprocess
    );
    
    this.cbs.addCapabilityBusinessProcess(capabilityid, newCapabilityBusinessprocess);
  }

}

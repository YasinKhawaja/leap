import { Component, OnInit } from '@angular/core';
import { Businessprocess } from 'src/app/classes/businessprocess/businessprocess';
import { BusinessprocessService } from 'src/app/services/businessprocess/businessprocess.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-businessprocess',
  templateUrl: './businessprocess.component.html',
  styleUrls: ['./businessprocess.component.css']
})
export class BusinessprocessComponent implements OnInit {

  businessprocesses: Businessprocess[];

  constructor(private bps: BusinessprocessService, private ns:NavbarService) { }

  ngOnInit(): void {
    let environmentid = this.ns.getEnvironmentCookie()
    this.bps.getBusinessProcesses(environmentid)
      .subscribe(
        result => {
          this.businessprocesses = result;
        },
        error => {
          Swal.fire('error', error.error.message, 'error')
        }
      )
  }

}

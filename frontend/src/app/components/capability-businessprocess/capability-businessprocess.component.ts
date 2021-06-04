import { Component, OnInit } from '@angular/core';
import { CapabilityBusinessprocess } from 'src/app/classes/capability-businessprocess/capability-businessprocess';
import { CapabilityBusinessprocessService } from 'src/app/services/capability-businessprocess/capability-businessprocess.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-capability-businessprocess',
  templateUrl: './capability-businessprocess.component.html',
  styleUrls: ['./capability-businessprocess.component.css']
})
export class CapabilityBusinessprocessComponent implements OnInit {
  
  capabilityBusinessProcesses: CapabilityBusinessprocess[];
  constructor(private cbp: CapabilityBusinessprocessService, private ns: NavbarService) { }

  ngOnInit(): void {
    var capabilityid = this.ns.getCapabilityCookie();

    this.cbp.getCapabilityBusinessProcess(capabilityid)
    .subscribe(
      result => {
        console.log(result);
        this.capabilityBusinessProcesses = result;
      },
      error => {
        Swal.fire('Error', error.error.message, 'error')
      }
    )    
  }

}

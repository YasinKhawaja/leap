import { Component, OnInit } from '@angular/core';
import { Businessprocess } from 'src/app/classes/businessprocess/businessprocess';
import { BusinessprocessService } from 'src/app/services/businessprocess/businessprocess.service';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-businessprocess',
  templateUrl: './businessprocess.component.html',
  styleUrls: ['./businessprocess.component.css']
})
export class BusinessprocessComponent implements OnInit {

  businessprocesses: Businessprocess[];

  constructor(private bps: BusinessprocessService, private ns: NavbarService, public jwt: JwtService) { }

  ngOnInit(): void {
    let environmentid = this.ns.getEnvironmentCookie()
    this.bps.getBusinessProcesses(environmentid)
      .subscribe(
        result => {
          this.businessprocesses = result;
        },
        () => {
          Swal.fire('error', `Failed to load business processes of environment`, 'error')
        }
      )
  }

  showProcessAdd: boolean = false;

  showProcessEdit: boolean = false;

  showProcessDelete: boolean = false;
  processCurrentValues: Businessprocess;
  show(component: string, process?: Businessprocess): void {
    switch (component) {
      case 'process-add':
        this.hideAll();
        // Show
        this.showProcessAdd = true;
        break;
      case 'process-edit':
        // Hide
        this.showProcessAdd = false;
        this.showProcessDelete = false;
        // Show
        this.processCurrentValues = process;
        this.showProcessEdit = !this.showProcessEdit;
        break;
        case 'process-delete':
          // Hide
          this.showProcessAdd = false;
          this.showProcessEdit = false;
          // Show
          this.processCurrentValues = process;
          this.showProcessDelete = !this.showProcessDelete;
          break;
 
      default:
        break;
    }
  }

  hideAll(): void {
    this.showProcessAdd = false;
    this.showProcessEdit = false;
    this.showProcessDelete= false;
  }

}

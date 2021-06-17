import { Component, OnInit } from '@angular/core';
import { Information } from 'src/app/classes/information/information';
import { InformationService } from 'src/app/services/information/information.service';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-information',
  templateUrl: './information.component.html',
  styleUrls: ['./information.component.css']
})
export class InformationComponent implements OnInit {

  informationList: Information[] = []


  constructor(private ns: NavbarService, public jwt: JwtService, private is: InformationService) { }

  ngOnInit() {
    this.getInformationList();
  }

  private getInformationList() {
    var environmentId = this.ns.getEnvironmentCookie();
    this.is.getInformationList(environmentId)
      .subscribe(
        (data) => {
          this.informationList = data
        },
        () => {
          Swal.fire('Error', `Failed to load information for environment (${environmentId})`, 'error')
        }
      )
  }

  showLinkedCapabilities(informationid: string) {
    //not implemented yet
  }


  setCapabilityCookie(capabilityid: string) {
    this.ns.setCapabilityCookie(capabilityid)
  }

  informationCurrentValues: Information
  showInformationAdd: boolean = false;
  showInformationEdit: boolean = false;
  showInformationDelete: boolean = false;
  //showInformationCapabilities: boolean = false;

  show(component: string, information?: Information) {
    switch (component) {
      case 'information-add':
        this.hideAll()
        this.showInformationAdd = true;
        break
      case 'information-edit':
        this.showInformationAdd = false
        this.showInformationDelete = false
        //this.showInformationCapabilities = false

        this.informationCurrentValues = information
        this.showInformationEdit = true
        break
      case 'information-delete':
        this.showInformationAdd = false
        this.showInformationEdit = false
        //this.showInformationCapabilities = false

        this.informationCurrentValues = information
        this.showInformationDelete = !this.showInformationDelete
        break;
      //implement capinfo
    }
  }
  hideAll() {
    this.showInformationAdd = false
    this.showInformationDelete = false
    this.showInformationEdit = false
    //this.showInformationCapabilities = false
  }
}

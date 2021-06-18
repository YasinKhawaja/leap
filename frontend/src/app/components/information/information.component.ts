import { Component, OnInit } from '@angular/core';
import { CapabilityInformation } from 'src/app/classes/capability-information/capability-information';
import { Information } from 'src/app/classes/information/information';
import { CapabilityInformationService } from 'src/app/services/capability-information/capability-information.service';
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
  capinformation: CapabilityInformation[] = []

  constructor(private ns: NavbarService, public jwt: JwtService, private is: InformationService, private cis: CapabilityInformationService) { }

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
    this.cis.getCapInfoListInformation(informationid)
      .subscribe(
        (data) => {
          this.capinformation = data;
          this.show('linked-caps');
        });
  }


  setCapabilityCookie(capabilityid: string) {
    this.ns.setCapabilityCookie(capabilityid)
  }

  informationCurrentValues: Information
  showInformationAdd: boolean = false;
  showInformationEdit: boolean = false;
  showInformationDelete: boolean = false;
  showLinkedCaps: boolean = false;

  show(component: string, information?: Information) {
    switch (component) {
      case 'information-add':
        this.hideAll()
        this.showInformationAdd = true;
        break
      case 'information-edit':
        this.showInformationAdd = false
        this.showInformationDelete = false
        this.showLinkedCaps = false

        this.informationCurrentValues = information
        this.showInformationEdit = true
        break
      case 'information-delete':
        this.showInformationAdd = false
        this.showInformationEdit = false
        this.showLinkedCaps = false

        this.informationCurrentValues = information
        this.showInformationDelete = !this.showInformationDelete
        break;
      case 'linked-caps':
        this.hideAll();

        this.showLinkedCaps = true;
        break;
      default:
        break;
    }
  }
  hideAll() {
    this.showInformationAdd = false
    this.showInformationDelete = false
    this.showInformationEdit = false
    this.showLinkedCaps = false
  }
}

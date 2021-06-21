import { Component, OnInit } from '@angular/core';
import { CapabilityStrategyItems } from 'src/app/classes/capability-strategyitems/capability-strategyitems';
import { Capability } from 'src/app/classes/capability/capability';
import { CapabilityStrategyitemService } from 'src/app/services/capability-strategyitem/capability-strategyitem.service';
import { CapabilityService } from 'src/app/services/capability/capability.service';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

@Component({
  selector: 'app-capability-strategyitems',
  templateUrl: './capability-strategyitems.component.html',
  styleUrls: ['./capability-strategyitems.component.css']
})
export class CapabilityStrategyitemsComponent implements OnInit {

  capability: Capability;

  capabilityStrategyItems: CapabilityStrategyItems[];

  constructor(private csis: CapabilityStrategyitemService, private ns: NavbarService, public jwt: JwtService, private cs: CapabilityService) { }

  ngOnInit(): void {
    this.getCapability();
    let capabilityId = this.ns.getCapabilityCookie();

    this.csis.getCapabilityStrategyItems(capabilityId)
      .subscribe(
        result => {
          this.capabilityStrategyItems = result;
        },
        error => console.log(error));
  }

  private getCapability(): void {
    var envId = this.ns.getEnvironmentCookie(), capId = this.ns.getCapabilityCookie();
    this.cs.getCapability(envId, capId).subscribe(response => this.capability = response);
  }

  showCapStrategyItemAdd: boolean = false;

  showCapStrategyItemEdit: boolean = false;

  showCapStrategyItemDelete: boolean = false;
  capStrategyItemCurrentValues: CapabilityStrategyItems;
  show(component: string, capstrategyItem?: CapabilityStrategyItems): void {
    switch (component) {
      case 'capstritem-add':
        this.hideAll();
        this.showCapStrategyItemAdd = true;
        break;
      case 'capstritem-edit':
        this.showCapStrategyItemAdd = false;
        this.showCapStrategyItemDelete = false;
        this.capStrategyItemCurrentValues = capstrategyItem;
        this.showCapStrategyItemEdit = !this.showCapStrategyItemEdit;
        break;
        case 'capstritem-delete':
          this.showCapStrategyItemAdd = false;
          this.showCapStrategyItemEdit = false;
          this.capStrategyItemCurrentValues = capstrategyItem;
          this.showCapStrategyItemDelete = !this.showCapStrategyItemDelete;
          break;
 
      default:
        break;
    }
  }

  hideAll(): void {
    this.showCapStrategyItemAdd = false;
    this.showCapStrategyItemEdit = false;
    this.showCapStrategyItemDelete= false;
  }
}

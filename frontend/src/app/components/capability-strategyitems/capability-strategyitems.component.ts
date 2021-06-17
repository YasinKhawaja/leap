import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CapabilityStrategyItems } from 'src/app/classes/capability-strategyitems/capability-strategyitems';
import { CapabilityStrategyitemService } from 'src/app/services/capability-strategyitem/capability-strategyitem.service';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

@Component({
  selector: 'app-capability-strategyitems',
  templateUrl: './capability-strategyitems.component.html',
  styleUrls: ['./capability-strategyitems.component.css']
})
export class CapabilityStrategyitemsComponent implements OnInit {

  capabilityStrategyItems: CapabilityStrategyItems[];

  constructor(private csis: CapabilityStrategyitemService, private router: Router, private ns: NavbarService, public jwt: JwtService) { }

  ngOnInit(): void {
    let capabilityId = this.ns.getCapabilityCookie();

    this.csis.getCapabilityStrategyItems(capabilityId)
      .subscribe(
        result => {
          console.log(result);
          this.capabilityStrategyItems = result;
          //this.router.navigate([])
        },
        error => console.log(error));
  }

  showCapStrategyItemAdd: boolean = false;

  showCapStrategyItemEdit: boolean = false;

  showCapStrategyItemDelete: boolean = false;
  capStrategyItemCurrentValues: CapabilityStrategyItems;
  show(component: string, capstrategyItem?: CapabilityStrategyItems): void {
    switch (component) {
      case 'capstritem-add':
        this.hideAll();
        // Show
        this.showCapStrategyItemAdd = true;
        break;
      case 'capstritem-edit':
        // Hide
        this.showCapStrategyItemAdd = false;
        this.showCapStrategyItemDelete = false;
        // Show
        this.capStrategyItemCurrentValues = capstrategyItem;
        this.showCapStrategyItemEdit = !this.showCapStrategyItemEdit;
        break;
        case 'capstritem-delete':
          // Hide
          this.showCapStrategyItemAdd = false;
          this.showCapStrategyItemEdit = false;
          // Show
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

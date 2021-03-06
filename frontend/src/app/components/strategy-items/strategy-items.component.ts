import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CapabilityStrategyItems } from 'src/app/classes/capability-strategyitems/capability-strategyitems';
import { StrategyItem } from 'src/app/classes/strategy-item/strategyItem';
import { CapabilityStrategyitemService } from 'src/app/services/capability-strategyitem/capability-strategyitem.service';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { StrategyItemService } from 'src/app/services/strategy-item/strategy-item.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-strategy-items',
  templateUrl: './strategy-items.component.html',
  styleUrls: ['./strategy-items.component.css']
})
export class StrategyItemsComponent implements OnInit {

  strategyItems: StrategyItem[]

  capStrItems: CapabilityStrategyItems[]

  constructor(private cs: StrategyItemService, private router: Router, public jwt: JwtService,
    private csi: CapabilityStrategyitemService) {
    this.strategyItems = [];
  }

  ngOnInit(): void {

    var strId = this.router.url.split('/')[2];

    this.cs.getAllStrategyItemInStrategy(strId)
      .subscribe(
        result => {
          this.strategyItems = result;
        },
        (error: HttpErrorResponse) => {
          Swal.fire('Error', error.error, 'error')
        });
  }


  showStrItemAdd: boolean = false;

  showStrItemEdit: boolean = false;
  showStrItemDelete: boolean = false;
  strItemCurrentValues: StrategyItem;

  showLinkedCaps: boolean = false;

  showLinkedCapabilities(strItemName: string): void {
    this.csi.getCapabilityStrategyItemsLinkedToStrategyItem(strItemName)
      .subscribe(response => {
        this.capStrItems = response;
        this.show('linked-caps');
      });
  }

  show(component: string, strategyItem?: StrategyItem): void {
    switch (component) {
      case 'strategyItem-add':
        this.hideAll();
        this.showStrItemAdd = true;
        break;
      case 'strategyItem-edit':
        this.showStrItemAdd = false;
        this.showStrItemDelete = false;
        this.showLinkedCaps = false;
        this.strItemCurrentValues = strategyItem;
        this.showStrItemEdit = !this.showStrItemEdit;
        break;

      case 'strategyItem-delete':
        this.showStrItemAdd = false;
        this.showStrItemEdit = false;
        this.showLinkedCaps = false;
        this.strItemCurrentValues = strategyItem;
        this.showStrItemDelete = !this.showStrItemDelete;
        break;
      case 'linked-caps':
        this.hideAll();
        this.showLinkedCaps = true;
        break;
      default:
        break;
    }
  }

  hideAll(): void {
    this.showStrItemAdd = false;
    this.showStrItemEdit = false;
    this.showStrItemDelete = false;
    this.showLinkedCaps = false;
  }

}

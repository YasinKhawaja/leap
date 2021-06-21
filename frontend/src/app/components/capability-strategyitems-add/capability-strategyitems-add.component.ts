import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { CapabilityStrategyItems } from 'src/app/classes/capability-strategyitems/capability-strategyitems';
import { CapabilityStrategyitemService } from 'src/app/services/capability-strategyitem/capability-strategyitem.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { StrategyItemService } from 'src/app/services/strategy-item/strategy-item.service';
import { StrategyService } from 'src/app/services/strategy/strategy.service';
import Swal from 'sweetalert2';
import { CapabilityStrategyitemsComponent } from '../capability-strategyitems/capability-strategyitems.component';


enum StrategicEmphasis {

  NONE = 'NONE',
  LOW = 'LOW',
  MEDIUM = 'MEDIUM',
  HIGH = 'HIGH'
}


@Component({
  selector: 'app-capability-strategyitems-add',
  templateUrl: './capability-strategyitems-add.component.html',
  styleUrls: ['./capability-strategyitems-add.component.css']
})
export class CapabilityStrategyitemsAddComponent implements OnInit {

  eSe = StrategicEmphasis;


  strategies: string[]

  strategy = this.fb.group({
    strategyName: ['']
  })

  strategyItems: string[]

  capabilityStrategyItem = this.fb.group({
    strategyItemName: ['', Validators.required],
    strategicEmphasis: ['', Validators.required]
  })

  constructor(private fb: FormBuilder, private css: CapabilityStrategyitemService,
    private ns: NavbarService, private sis: StrategyService, private sos: StrategyItemService, private csic: CapabilityStrategyitemsComponent) {

    this.strategies = [];
    this.strategyItems = [];
  }

  ngOnInit(): void {
    let environmentId = this.ns.getEnvironmentCookie();
    this.sis.getAllStrategyInEnvironment(environmentId)
      .subscribe(result => {
        result.forEach(e => {
          this.strategies.push(e.name);
        })
      },
        (error: HttpErrorResponse) => {
          Swal.fire('Error', error.error, 'error')
        });
  }

  hide(): void {
    this.csic.hideAll();
  }

  changeStrategy() {
    this.strategyItems = [];

    this.sos.getAllStrategyItemsInStrategyByName(this.strategy.value.strategyName)
      .subscribe(result => {
        result.forEach(e => {

          this.strategyItems.push(e.name);
        })
      },
        (error: HttpErrorResponse) => {
          Swal.fire('Error', error.error, 'error')
        });
  }

  changeStrategyItem() {
    this.capabilityStrategyItem.value.strategyItemName;
  }

  onSubmit() {
    let capabilityId = this.ns.getCapabilityCookie();

    var newCapabilityStrategyItem = new CapabilityStrategyItems(
      this.capabilityStrategyItem.value.strategyItemName,
      this.capabilityStrategyItem.value.strategicEmphasis
    );

    this.css.createCapabilityStrategyItem(capabilityId, newCapabilityStrategyItem)
      .subscribe(
        () => {
          this.csic.ngOnInit();
        },
        () => {
          Swal.fire('Error', `Failed to add capability strategy item link`, 'error')
        }
      )
  }
}

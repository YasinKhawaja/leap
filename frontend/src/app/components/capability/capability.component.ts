import { Component, OnInit } from '@angular/core';
import { Capability } from 'src/app/classes/capability/capability';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { CapabilityService } from '../../services/capability/capability.service';

@Component({
  selector: 'app-capability',
  templateUrl: './capability.component.html',
  styleUrls: ['./capability.component.css']
})

export class CapabilityComponent implements OnInit {

  capabilities: Capability[];
  // For setCapability()
  _cap: Capability;
  // For display()
  _display1: boolean = false;
  _display2: boolean = false;
  _display3: boolean = false;

  constructor(private cs: CapabilityService, private ns: NavbarService) {
    this._cap = null;
  }

  ngOnInit(): void {
    this.ns.environmentSelect();

    var envId = this.ns.getEnvironment();

    this.cs.getAllCapabilitiesInEnvironment(envId)
      .subscribe(res => { this.capabilities = res; console.log(res); }, err => console.error(err));
  }

  // To set the _cap prop to give its value to child comps
  private setCapability(cap: Capability) {
    this._cap = cap;
  }

  // To display the "capability-add" comp
  private display(column: string) {
    switch (column) {
      case '1':
        this._display1 = !this._display1;
        this._display2 = false;
        this._display3 = false;
        break;
      case '2':
        this._display1 = false;
        this._display2 = !this._display2;
        this._display3 = false;
        break;
      case '3':
        this._display1 = false;
        this._display2 = false;
        this._display3 = !this._display3;
        break;
      default:
        break;
    }
  }

  // To call the 2 methods above
  callAll(cap: Capability, column: string) {
    this.setCapability(cap);
    this.display(column);
  }

}
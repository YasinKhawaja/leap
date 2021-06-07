import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CapabilityStrategyItems } from 'src/app/classes/capability-strategyitems/capability-strategyitems';
import { CapabilityStrategyitemService } from 'src/app/services/capability-strategyitem/capability-strategyitem.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

@Component({
  selector: 'app-capability-strategyitems',
  templateUrl: './capability-strategyitems.component.html',
  styleUrls: ['./capability-strategyitems.component.css']
})
export class CapabilityStrategyitemsComponent implements OnInit {

  capabilityStrategyItems: CapabilityStrategyItems[];

  constructor(private csis: CapabilityStrategyitemService, private router: Router, private ns: NavbarService) { }

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
}

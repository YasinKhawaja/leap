import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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

  constructor(private cs: CapabilityService, private router: Router, private ns: NavbarService) { }

  ngOnInit(): void {
    this.ns.environmentSelect();

    var envId = this.ns.getEnvironment();
    // var envId = this.router.url.split('/')[2];

    this.cs.getAllCapabilitiesInEnvironment(envId)
      .subscribe(res => { this.capabilities = res; console.log(res); }, err => console.error(err));
  }

}
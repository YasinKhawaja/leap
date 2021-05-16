import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Capability } from 'src/app/classes/capability/capability';
import { CapabilityService } from '../../services/capability/capability.service';

@Component({
  selector: 'app-capability',
  templateUrl: './capability.component.html',
  styleUrls: ['./capability.component.css']
})

export class CapabilityComponent implements OnInit {

  caps: Capability[];

  constructor(private cs: CapabilityService, private router: Router) { }

  ngOnInit(): void {
    var envId = this.router.url.split('/')[2];

    this.cs.getAllCapabilitiesInEnvironment(envId)
      .subscribe(res => { this.caps = res; console.log(res); }, err => console.error(err));
  }

}
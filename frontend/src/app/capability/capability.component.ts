import { Component, OnInit } from '@angular/core';
import { Capability } from '../service/capability';
import { CapabilityService } from '../service/capability.service';

@Component({
  selector: 'app-capability',
  templateUrl: './capability.component.html',
  styleUrls: ['./capability.component.css']
})

export class CapabilityComponent implements OnInit {

  capabilities: Capability[]

  constructor(private cs: CapabilityService) { 
    this.capabilities = [];
  }

  ngOnInit(): void {
    this.cs.getAllCapabilities()
               .subscribe(data => { this.capabilities = data }, 
                          error => { console.error(error) })
}

}

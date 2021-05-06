import { Component, OnInit } from '@angular/core';
import { Capability } from '../../classes/capability/capability';
import { CapabilityService } from '../../services/capability/capability.service';

@Component({
  selector: 'app-export',
  templateUrl: './export.component.html',
  styleUrls: ['./export.component.css']
})
export class ExportComponent implements OnInit {

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

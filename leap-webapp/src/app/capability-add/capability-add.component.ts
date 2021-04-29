import { Component, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from "@angular/forms";
import { CapabilityService } from "../service/capability.service";
import { Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Capability } from '../service/capability';

enum PaceOfChange {
  NONE = 'NONE',
  STANDARD = 'STANDARD',
  DIFFERENTIATION = 'DIFFERENTIATION',
  INNOVATIVE = 'INNOVATIVE'
}

enum Tom {
  NONE = 'NONE',
  COORDINATION = 'COORDINATION',
  DIVERSIFICATION = 'DIVERSIFICATION',
  REPLICATION = 'REPLICATION',
  UNIFICATION = 'UNIFICATION'
}
@Component({
  selector: 'app-capability-add',
  templateUrl: './capability-add.component.html',
  styleUrls: ['./capability-add.component.css']
})
export class CapabilityAddComponent implements OnInit {

  ePaceOfChange = PaceOfChange;
  eTom = Tom;

  capability = this.fb.group({
    name: ['', Validators.required],
    level: ['', Validators.required],
    paceOfChange: ['', Validators.required],
    tom: ['', Validators.required],
    resourcesQuality: ['', Validators.required],
    informationQuality: ['', Validators.required],
    applicationFit: ['', Validators.required]

  })

  constructor(private fb: FormBuilder,
              private router: Router,
              private cs: CapabilityService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    //this.cs.addCapability(new Capability(this.capability.value.name))
    this.cs.addCapability(new Capability(this.capability.value.name, this.capability.value.level,
      this.capability.value.paceOfChange, this.capability.value.tom, this.capability.value.resourcesQuality,
      this.capability.value.informationQuality, this.capability.value.applicationFit))
    this.router.navigate(['capabilities'])
  }

}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from "@angular/forms";
import { CapabilityService } from "../service/capability.service";
import { Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Capability } from '../service/capability';

@Component({
  selector: 'app-capability-add',
  templateUrl: './capability-add.component.html',
  styleUrls: ['./capability-add.component.css']
})
export class CapabilityAddComponent implements OnInit {

  capability = this.fb.group({
    name: ['', Validators.required]
  })

  constructor(private fb: FormBuilder,
              private router: Router,
              private cs: CapabilityService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.cs.addCapability(new Capability(this.capability.value.name))
    this.router.navigate(['capabilities'])
  }

}

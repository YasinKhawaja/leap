import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CapabilityService } from '../service/capability.service';

@Component({
  selector: 'app-capability-delete',
  templateUrl: './capability-delete.component.html',
  styleUrls: ['./capability-delete.component.css']
})
export class CapabilityDeleteComponent implements OnInit {

  capabilityName: string
  capability = this.fb.group({
    name: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]]
  })

  constructor(private cs: CapabilityService,
              private fb: FormBuilder,
              private router: Router) {

        // decode the capability name parameter from the url because otherwise 'Capability Name' will be filled in
        // in the delete form as 'Capability%20Name'
        this.capabilityName = decodeURI(router.url.split('/')[3])
        this.capability.controls['name'].setValue(this.capabilityName)        
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.cs.deleteCapability(this.capability.value.name)
    this.router.navigate(['capabilities'])
  }

}

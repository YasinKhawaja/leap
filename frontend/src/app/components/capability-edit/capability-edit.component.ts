import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import { Capability } from '../../classes/capability/capability';
import { CapabilityService } from '../../services/capability/capability.service';

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
  selector: 'app-capability-edit',
  templateUrl: './capability-edit.component.html',
  styleUrls: ['./capability-edit.component.css']
})
export class CapabilityEditComponent implements OnInit {

  ePaceOfChange = PaceOfChange
  eTom = Tom;
  capabilityName: string

  capabilityForm = this.fb.group({
    name: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
    paceOfChange: ['', Validators.required],
    tom: ['', Validators.required],
    resourcesQuality: ['', [Validators.required, Validators.pattern('[1-5]')]]
  });

  constructor(private es: EnvironmentService, private cs: CapabilityService,
    private fb: FormBuilder, private router: Router, private activeroute: ActivatedRoute) {
    // // splitting url parts
    // this.capabilityName = router.url.split('/')[3]
    // // handling active route
    // this.activeroute.params
    //   .subscribe(params => {
    //     this.capabilityName = params['name'];
    //     this.cs.searchOneCapability(this.capabilityName)
    //       .subscribe(data => {
    //         console.log(data),
    //           this.capabilityForm.controls['name'].setValue(data[0].name)
    //         this.capabilityForm.controls['paceOfChange'].setValue(data[0].paceOfChange)
    //         this.capabilityForm.controls['tom'].setValue(data[0].tom)
    //         this.capabilityForm.controls['resourcesQuality'].setValue(data[0].resourcesQuality)
    //       },
    //         error => { console.log(error) })
    //   }
    //   )
  }

  ngOnInit(): void { }

  refer() {
    var capabilityId = this.router.url.split('/')[4];
    console.log("TEST");
    this.router.navigate([`capability-application/${capabilityId}`])
  }

  onSubmit() {
    var envId = this.router.url.split('/')[2];
    var capIdToUpdate = this.router.url.split('/')[4];

    var newCapValues = new Capability(
      this.capabilityForm.value.name,
      this.capabilityForm.value.paceOfChange,
      this.capabilityForm.value.tom,
      this.capabilityForm.value.resourcesQuality
    );

    this.cs.updateCapabilityInEnvironment(envId, capIdToUpdate, newCapValues)
      .subscribe(res => console.log(res), err => console.error(err));

    this.router.navigate([`envs/${envId}/caps`])//.then(() => window.location.reload());
  }

}
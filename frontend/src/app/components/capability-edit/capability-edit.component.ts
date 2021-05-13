import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Environment } from 'src/app/classes/environment/environment';
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

  environment: Environment
  capability: Capability

  capabilityForm = this.fb.group({
    name: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
    paceOfChange: ['', Validators.required],
    tom: ['', Validators.required],
    resourcesQuality: ['', [Validators.required, Validators.pattern('[1-5]')]]
  });

  constructor(private es: EnvironmentService, private cs: CapabilityService,
    private fb: FormBuilder, private router: Router, private activeroute: ActivatedRoute) {
    // splitting url parts
    this.capabilityName = router.url.split('/')[3]
    // handling active route
    this.activeroute.params
      .subscribe(params => {
        this.capabilityName = params['name'];
        this.cs.searchOneCapability(this.capabilityName)
          .subscribe(data => {
            console.log(data),
              this.capabilityForm.controls['name'].setValue(data[0].name)
            this.capabilityForm.controls['paceOfChange'].setValue(data[0].paceOfChange)
            this.capabilityForm.controls['tom'].setValue(data[0].tom)
            this.capabilityForm.controls['resourcesQuality'].setValue(data[0].resourcesQuality)
          },
            error => { console.log(error) })
      }
      )
  }

  ngOnInit(): void {
    var envName = this.router.url.split('/')[2];
    var capName = this.router.url.split('/')[4];

    this.es.getEnvironmentByName(envName)
      .subscribe(response => { this.environment = response; console.log(response); }, error => console.log(error));

    this.cs.getCapabilityByName(envName, capName)
      .subscribe(response => { this.capability = response; console.log(response); }, error => console.log(error));
  }

  onSubmit() {
    var capToUpdate = new Capability(
      this.capabilityForm.value.name,
      this.capabilityForm.value.paceOfChange,
      this.capabilityForm.value.tom,
      this.capabilityForm.value.resourcesQuality
    );

    this.cs.updateCapabilityInEnvironment(this.environment.id, this.capability.id, capToUpdate)
      .subscribe(response => console.log(response), error => console.log(error));

    this.router.navigate([`environments/${this.environment.name}/capabilities`]).then(() => window.location.reload());
  }

}
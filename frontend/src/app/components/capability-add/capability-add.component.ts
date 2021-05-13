import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Environment } from 'src/app/classes/environment/environment';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import Swal from 'sweetalert2';
import { Capability } from '../../classes/capability/capability';
import { CapabilityService } from "../../services/capability/capability.service";

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
  environment: Environment

  capability = this.fb.group({
    name: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
    paceOfChange: ['', Validators.required],
    tom: ['', Validators.required],
    resourcesQuality: ['', [Validators.required, Validators.pattern('[1-5]')]]
  });

  constructor(private fb: FormBuilder, private router: Router, private cs: CapabilityService, private es: EnvironmentService) { }

  ngOnInit(): void {
    var name = this.router.url.split('/')[2];

    this.es.getEnvironmentByName(name)
      .subscribe(response => { this.environment = response; console.log(response); }, error => console.log(error));
  }

  onSubmit() {
    var capToCreate = new Capability(
      this.capability.value.name,
      this.capability.value.paceOfChange,
      this.capability.value.tom,
      this.capability.value.resourcesQuality
    );

    this.cs.createCapabilityInEnvironment(this.environment.id, capToCreate)
      .subscribe(
        response => console.log(response),
        error => { if (error.error.message) Swal.fire('Error', error.error.message, 'error') }
      );

    this.router.navigate([`environments/${this.environment.name}/capabilities`])
      .then(() => window.location.reload());
  }

}
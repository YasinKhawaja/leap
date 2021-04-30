import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Capability } from '../service/capability';
import { CapabilityService } from '../service/capability.service';

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

  ePaceOfChange = PaceOfChange;
  eTom = Tom;

  capabilityName: string

  capability = this.fb.group({
    name: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
    level: ['', [Validators.required, Validators.pattern('[0-9]')]],
    paceOfChange: ['', Validators.required],
    tom: ['', Validators.required],
    resourcesQuality: ['', [Validators.required, Validators.pattern('[0-9]')]],
    informationQuality: ['', [Validators.required, Validators.pattern('[0-9]+[.][0-9]+')]],
    applicationFit: ['', [Validators.required, Validators.pattern('[0-9]+[.][0-9]+')]]

  })

  constructor(private cs: CapabilityService,
    private fb: FormBuilder,
    private router: Router,
    private activeroute: ActivatedRoute) {

    // splitting url parts
    this.capabilityName = router.url.split('/')[3]
    // handling active route
    this.activeroute.params
      .subscribe(params => {
        this.capabilityName = params['name'];
        this.cs.searchOneCapability(this.capabilityName)
          .subscribe(data => { console.log(data),
            this.capability.controls['name'].setValue(data[0].name)
            this.capability.controls['level'].setValue(data[0].level)
            this.capability.controls['paceOfChange'].setValue(data[0].paceOfChange)
            this.capability.controls['tom'].setValue(data[0].tom)
            this.capability.controls['resourcesQuality'].setValue(data[0].resourcesQuality)
            this.capability.controls['informationQuality'].setValue(data[0].informationQuality)
            this.capability.controls['applicationFit'].setValue(data[0].applicationFit)
          },
            error => { console.log(error) })
      }
      )
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.cs.editCapability(new Capability(this.capability.value.name,
                                          this.capability.value.level,
                                          this.capability.value.paceOfChange,
                                          this.capability.value.tom,
                                          this.capability.value.resourcesQuality,
                                          this.capability.value.informationQuality,
                                          this.capability.value.applicationFit),
                                          this.capabilityName)
    
    this.router.navigate(['capabilities'])
  }

}

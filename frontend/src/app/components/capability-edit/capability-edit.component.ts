import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
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

  ePaceOfChange = PaceOfChange;
  eTom = Tom;

  capabilityName: string

  capability = this.fb.group({
    name: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
    paceOfChange: ['', Validators.required],
    tom: ['', Validators.required],
    resourcesQuality: ['', [Validators.required, Validators.pattern('[1-5]')]]

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
            this.capability.controls['paceOfChange'].setValue(data[0].paceOfChange)
            this.capability.controls['tom'].setValue(data[0].tom)
            this.capability.controls['resourcesQuality'].setValue(data[0].resourcesQuality)
          },
            error => { console.log(error) })
      }
      )
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.cs.editCapability(new Capability(this.capability.value.name,
                                          this.capability.value.paceOfChange,
                                          this.capability.value.tom,
                                          this.capability.value.resourcesQuality),
                                          this.capabilityName)
    
    this.router.navigate(['capabilities'])
      .then(() => {
        window.location.reload();
      })
  }

}

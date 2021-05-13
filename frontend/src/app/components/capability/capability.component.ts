import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Environment } from 'src/app/classes/environment/environment';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import { CapabilityService } from '../../services/capability/capability.service';

@Component({
  selector: 'app-capability',
  templateUrl: './capability.component.html',
  styleUrls: ['./capability.component.css']
})

export class CapabilityComponent implements OnInit {

  environment: Environment

  constructor(private es: EnvironmentService, private cs: CapabilityService, private router: Router) { }

  ngOnInit(): void {
    var name = this.router.url.split('/')[2];

    this.es.getEnvironmentByName(name)
      .subscribe(response => { this.environment = response; console.log(response); }, error => console.error(error));
  }

}
import { Component, OnInit } from '@angular/core';
import { Environment } from 'src/app/classes/environment/environment';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

@Component({
  selector: 'app-environment',
  templateUrl: './environment.component.html',
  styleUrls: ['./environment.component.css']
})
export class EnvironmentComponent implements OnInit {

  environments: Environment[];

  constructor(private es: EnvironmentService, private ns: NavbarService) {
    this.environments = [];
  }

  ngOnInit(): void {
    this.getAllEnvironments();
  }

  getAllEnvironments(): void {
    this.es.getAllEnvironments()
      .subscribe(
        res => { this.environments = res; console.log(res); },
        err => console.error(err)
      );
  }

  environmentId(environmentId): void {
    this.ns.setEnvironment(environmentId);
  }
}
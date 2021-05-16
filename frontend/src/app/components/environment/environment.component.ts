import { Component, OnInit } from '@angular/core';
import { Environment } from 'src/app/classes/environment/environment';
import { EnvironmentService } from 'src/app/services/environment/environment.service';

@Component({
  selector: 'app-environment',
  templateUrl: './environment.component.html',
  styleUrls: ['./environment.component.css']
})
export class EnvironmentComponent implements OnInit {

  environments: Environment[];

  constructor(private es: EnvironmentService) {
    this.environments = [];
  }

  ngOnInit(): void {
    this.es.getAllEnvironments()
      .subscribe(res => { this.environments = res; console.log(res); }, err => console.error(err));
  }

}
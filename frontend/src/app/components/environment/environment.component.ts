import { Component, OnInit } from '@angular/core';
import { Environment } from 'src/app/classes/environment/environment';
import { EnvironmentService } from 'src/app/services/environment/environment.service';

@Component({
  selector: 'app-environment',
  templateUrl: './environment.component.html',
  styleUrls: ['./environment.component.css']
})
export class EnvironmentComponent implements OnInit {

  environments: Environment[]

  displayEditEnv = false;

  constructor(private es: EnvironmentService) {
    this.environments = [];
  }

  ngOnInit(): void {
    this.es.getEnvironments()
      .subscribe(data => { this.environments = data; console.log(data); }, error => { console.error(error) });
  }

}
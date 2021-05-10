import { Component, OnInit } from '@angular/core';
import { Environment } from 'src/app/classes/environment/environment';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import { EnvironmentEditComponent } from '../environment-edit/environment-edit.component';

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
    this.es.getAllEnvironments()
      .subscribe(data => { this.environments = data; console.log(data); }, error => { console.error(error) })
  }

  editEnvironment(): void {
    this.displayEditEnv = !this.displayEditEnv;
  }

  deleteEnvironment(environmentName: string): void {
    this.es.deleteEnvironment(environmentName).subscribe();
    this.es.getAllEnvironments().subscribe();
  }

}
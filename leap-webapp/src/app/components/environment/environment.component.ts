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

  constructor(private es: EnvironmentService) {
    this.environments = [];
  }

  ngOnInit(): void {
    this.es.getAllEnvironments()
      .subscribe(data => { this.environments = data }, error => { console.error(error) })
  }

  updateEnvironment(id: number, name: string): void {
    this.es.updateEnvironment(id, name);
    this.es.refresh();
  }

  deleteEnvironment(id: number): void {
    this.es.deleteEnvironment(id);
    this.es.refresh();
  }

}
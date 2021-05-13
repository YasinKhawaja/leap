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

  constructor(private es: EnvironmentService) { }

  ngOnInit(): void {
    this.es.getEnvironments()
      .subscribe(response => { this.environments = response; console.log(response); }, error => console.error(error));
  }

}
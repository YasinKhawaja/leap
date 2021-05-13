import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Environment } from 'src/app/classes/environment/environment';
import { EnvironmentService } from 'src/app/services/environment/environment.service';

@Component({
  selector: 'app-environment-delete',
  templateUrl: './environment-delete.component.html',
  styleUrls: ['./environment-delete.component.css']
})
export class EnvironmentDeleteComponent implements OnInit {

  environment: Environment

  constructor(private es: EnvironmentService, private router: Router) { }

  ngOnInit(): void {
    var name = this.router.url.split('/')[2];

    this.es.getEnvironmentByName(name)
      .subscribe(response => this.environment = response, error => console.log(error));
  }

  deleteEnvironment(id: number): void {
    this.es.deleteEnvironment(id)
      .subscribe(response => console.log(response), error => console.log(error));

    this.router.navigate(['/environments']);
  }

}
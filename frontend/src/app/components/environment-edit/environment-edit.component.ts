import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Environment } from 'src/app/classes/environment/environment';
import { EnvironmentService } from 'src/app/services/environment/environment.service';

@Component({
  selector: 'app-environment-edit',
  templateUrl: './environment-edit.component.html',
  styleUrls: ['./environment-edit.component.css']
})
export class EnvironmentEditComponent implements OnInit {

  environment = this.fb.group({
    name: ['', Validators.required]
  });

  constructor(private es: EnvironmentService, private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void { }

  onSubmit(): void {
    var environmentName = this.router.url.split('/')[2];

    var environmentRequest = new Environment(this.environment.value.name);

    this.es.updateEnvironment(environmentName, environmentRequest).subscribe(data => console.log(data));

    // works like refresh
    this.es.getAllEnvironments().subscribe();

    this.router.navigate(['/environments']);
  }

}

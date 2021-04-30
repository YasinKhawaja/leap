import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Environment } from 'src/app/classes/environment/environment';
import { EnvironmentService } from 'src/app/services/environment/environment.service';

@Component({
  selector: 'app-environment',
  templateUrl: './environment.component.html',
  styleUrls: ['./environment.component.css']
})
export class EnvironmentComponent implements OnInit {

  environment = this.fb.group({
    name: ['', Validators.required]
  })

  constructor(private fb: FormBuilder, private es: EnvironmentService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    var environment = new Environment(this.environment.value.name);

    this.es.addEnvironment(environment);
  }

}

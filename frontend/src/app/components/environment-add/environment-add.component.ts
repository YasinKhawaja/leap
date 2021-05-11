import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Environment } from 'src/app/classes/environment/environment';
import { EnvironmentService } from 'src/app/services/environment/environment.service';

@Component({
  selector: 'app-environment-add',
  templateUrl: './environment-add.component.html',
  styleUrls: ['./environment-add.component.css']
})
export class EnvironmentAddComponent implements OnInit {

  environment = this.fb.group({
    name: ['', Validators.required]
  });

  constructor(private es: EnvironmentService, private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    var environmentToCreate = new Environment(this.environment.value.name);

    this.es.createEnvironment(environmentToCreate).subscribe(data => console.log(data));

    this.router.navigate(['environments'])
      .then(() => {
      window.location.reload();
    });
  }

}

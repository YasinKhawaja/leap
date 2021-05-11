import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Environment } from 'src/app/classes/environment/environment';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-environment-edit',
  templateUrl: './environment-edit.component.html',
  styleUrls: ['./environment-edit.component.css']
})
export class EnvironmentEditComponent implements OnInit {

  token: string

  environment = this.fb.group({
    name: ['', Validators.required]
  });

  constructor(private es: EnvironmentService, private fb: FormBuilder, private router: Router, private ls: LoginService) { 
    this.token = "";
   }

  ngOnInit(): void { 
    this.ls.getUserToken()
    .subscribe(data => { 
      this.token = data;
      console.log(data); },
      error => {console.error(error)})

   }

  onSubmit(): void {
    var environmentName = this.router.url.split('/')[2];

    var environmentRequest = new Environment(this.environment.value.name);

    this.es.updateEnvironment(environmentName, environmentRequest).subscribe(data => console.log(data));

    // works like refresh
    //this.es.getAllEnvironments().subscribe();

    this.router.navigate(['/environments'])
      .then(() => {
      window.location.reload();
    });
  }

}

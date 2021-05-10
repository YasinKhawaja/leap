import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Environment } from 'src/app/classes/environment/environment';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-environment-add',
  templateUrl: './environment-add.component.html',
  styleUrls: ['./environment-add.component.css']
})
export class EnvironmentAddComponent implements OnInit {

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
    var environment = new Environment(this.environment.value.name);

    this.es.addEnvironment(environment).subscribe(data => console.log(data));

    // works like refresh
    this.es.getAllEnvironments().subscribe();

    this.router.navigate(['environments']);
  }

}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-environment-edit',
  templateUrl: './environment-edit.component.html',
  styleUrls: ['./environment-edit.component.css']
})
export class EnvironmentEditComponent implements OnInit {

  token: string;

  environmentForm = this.fb.group({
    name: ['', Validators.required]
  });

  constructor(private es: EnvironmentService, private fb: FormBuilder, private router: Router, private ls: LoginService) {
    this.token = "";
  }

  ngOnInit(): void {
    this.ls.getUserToken()
      .subscribe(data => {
        this.token = data;
        console.log(data);
      },
        error => { console.error(error) });
  }

  onSubmit(): void {
    var envIdToUpdate = this.router.url.split('/')[2];
    var newEnvName = this.environmentForm.value.name;

    this.es.updateEnvironment(envIdToUpdate, newEnvName)
      .subscribe(res => console.log(res), err => console.error(err));

    this.router.navigate(['/envs']);
  }

}
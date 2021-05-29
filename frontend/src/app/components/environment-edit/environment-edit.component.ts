import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import { LoginService } from 'src/app/services/login/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-environment-edit',
  templateUrl: './environment-edit.component.html',
  styleUrls: ['./environment-edit.component.css']
})
export class EnvironmentEditComponent implements OnInit {

  token: string;

  envEditForm = this.fb.group({
    name: ['', Validators.required]
  });

  constructor(private es: EnvironmentService, private fb: FormBuilder, private router: Router, private ls: LoginService) {
  }

  ngOnInit(): void {
  }

  // To be able to use all form controls (name) above in html
  get envEditFormControls() { return this.envEditForm.controls; }

  onSubmit(): void {
    var envIdToUpdate = this.router.url.split('/')[2];
    var newEnvName = this.envEditForm.value.name;

    this.es.updateEnvironment(envIdToUpdate, newEnvName)
      .subscribe(
        res => {
          console.log(res);
          Swal.fire('Edited', 'Environment renamed.', 'success');
          this.router.navigate(['environments']);
        },
        err => Swal.fire('Error', err.error.message, 'error')
      );
  }

}
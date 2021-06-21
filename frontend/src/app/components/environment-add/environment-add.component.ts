import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-environment-add',
  templateUrl: './environment-add.component.html',
  styleUrls: ['./environment-add.component.css']
})
export class EnvironmentAddComponent implements OnInit {

  envAddForm: FormGroup;
  companyid: string;

  constructor(private es: EnvironmentService, private fb: FormBuilder, private router: Router, private jwt: JwtService) { }

  ngOnInit(): void {
    this.initializeForm();
    this.companyid = this.jwt.checkCompany();
  }

  // To initialize the envAddForm
  initializeForm() {
    // Form group
    this.envAddForm = this.fb.group({
      // Form controls
      name: ['', [Validators.required]],
    });
  }

  // To be able to use the name form control in HTML
  get name() { return this.envAddForm.get('name'); }

  onSubmit(): void {
    this.es.createEnvironment(this.name.value, this.companyid)
      .subscribe(
        () => {
          Swal.fire('Created', `Environment <strong>${this.name.value}</strong> created.`, 'success');
          this.router.navigate(['environments']);
        },
        (error: HttpErrorResponse) => {
          Swal.fire('Error', error.error, 'error')
        });
  }

}
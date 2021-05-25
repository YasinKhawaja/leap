import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-environment-add',
  templateUrl: './environment-add.component.html',
  styleUrls: ['./environment-add.component.css']
})
export class EnvironmentAddComponent implements OnInit {

  envAddForm: FormGroup;

  constructor(private es: EnvironmentService, private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  // To initialize the envAddForm
  initializeForm() {
    // Form group
    this.envAddForm = this.fb.group({
      // Form controls
      name: ['', [Validators.required, Validators.pattern('[a-zA-Z]+')]],
    });
  }

  // To be able to use the name form control in HTML
  get name() { return this.envAddForm.get('name'); }

  onSubmit(): void {
    this.es.createEnvironment(this.name.value)
      .subscribe(
        res => {
          console.log(res);
          Swal.fire('Created', `Environment ${this.name.value} created.`, 'success');
          this.router.navigate(['environments']);
        },
        err => Swal.fire('Error', err.error.message, 'error')
      );
  }

}
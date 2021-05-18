import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Environment } from 'src/app/classes/environment/environment';
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
      name: ['', [Validators.required, this.noWhiteSpace]],
    });
  }

  // To be able to use all form controls (name) above in html
  get envAddFormControls() { return this.envAddForm.controls; }

  // Custom validator => It validates if there isn't a white space in the string
  private noWhiteSpace(control: AbstractControl): { [key: string]: any } | null {
    if ((control.value as string).indexOf(' ') >= 0) {
      return { 'whitespace': true };
    }
    return null;
  }

  onSubmit(): void {
    var env = new Environment(this.envAddFormControls.name.value);

    this.es.createEnvironment(env.name)
      .subscribe(res => console.log(res), err => Swal.fire('Error', 'This environment already exists!', 'error'));

    this.router.navigate(['environments'])//.then(() => window.location.reload());
  }

}
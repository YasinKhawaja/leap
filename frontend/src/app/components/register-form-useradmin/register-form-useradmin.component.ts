import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomErrorStateMatcher } from 'src/app/classes/errormatcher/custom-error-state-matcher';
import { User } from 'src/app/classes/user/user';
import { UserService } from 'src/app/services/user/user.service';
import sha256 from 'crypto-js/sha256';

const salt = "!sH@2.5.6?.-_#eNc0.d3Ds@L.t";

@Component({
  selector: 'app-register-form-useradmin',
  templateUrl: './register-form-useradmin.component.html',
  styleUrls: ['./register-form-useradmin.component.css']
})
export class RegisterFormUseradminComponent {

  matcher = new CustomErrorStateMatcher();

  useradmin = this.fb.group({
    firstName: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
    surname: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
    email: ['', [Validators.required, Validators.pattern("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$")]],
    username: ['', Validators.required],
    password: ['', [Validators.required, Validators.minLength(8)]],
    repeatPassword: ['', Validators.required]
  }, {
    validator: this.checkPasswords
  })

  constructor(private fb: FormBuilder,
    private cs: UserService) { }

  checkPasswords(group: FormGroup) {
    const password = group.controls.password.value;
    const repeatPassword = group.controls.repeatPassword.value;

    return password === repeatPassword ? null : { different: true }
  }

  get f() {
    return this.useradmin.controls;
  }

  onSubmit() {
    this.cs.register(new User(
      this.useradmin.value.firstName,
      this.useradmin.value.surname,
      this.useradmin.value.email,
      this.useradmin.value.username,
      sha256(this.useradmin.value.password + salt).toString()
    ))
  }
}

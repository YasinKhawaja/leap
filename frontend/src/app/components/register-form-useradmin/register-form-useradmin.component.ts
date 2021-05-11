import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomErrorStateMatcher } from 'src/app/classes/errormatcher/custom-error-state-matcher';
import { Useradmin } from 'src/app/classes/useradmin/useradmin';
import { UseradminService } from 'src/app/services/useradmin/useradmin.service';

@Component({
  selector: 'app-register-form-useradmin',
  templateUrl: './register-form-useradmin.component.html',
  styleUrls: ['./register-form-useradmin.component.css']
})
export class RegisterFormUseradminComponent implements OnInit{

  matcher = new CustomErrorStateMatcher();

  useradmin = this.fb.group({
    firstName: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
    surname: ['',  [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
    email: ['', [Validators.required,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
    username: ['',  Validators.required],
    password: ['', [Validators.required, Validators.minLength(8)]],
    repeatPassword: ['', Validators.required]
  }, {
    validator: this.checkPasswords
  })

  constructor(private fb: FormBuilder,
    private router: Router,
    private cs: UseradminService) { }

    checkPasswords(group: FormGroup){
      const password = group.controls.password.value;
      const repeatPassword = group.controls.repeatPassword.value;

      return password === repeatPassword ? null : { different: true }
    }
    
    ngOnInit(): void {
    }

    get f(){
      return this.useradmin.controls;
    }
  
    onSubmit() {
      this.cs.register(new Useradmin(
        this.useradmin.value.firstName,
        this.useradmin.value.surname,
        this.useradmin.value.email,
        this.useradmin.value.username,
        this.useradmin.value.password
        ))
        //success page not implemented yet
      this.router.navigate(['login'])
    }
  }

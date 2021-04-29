import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Useradmin } from 'src/app/classes/useradmin/useradmin';
import { UseradminService } from 'src/app/services/useradmin/useradmin.service';

@Component({
  selector: 'app-register-form-useradmin',
  templateUrl: './register-form-useradmin.component.html',
  styleUrls: ['./register-form-useradmin.component.css']
})
export class RegisterFormUseradminComponent implements OnInit{

  useradmin = this.fb.group({
    firstName: ['', Validators.required],
    surname: ['', Validators.required],
    email: ['', Validators.email],
    username: ['', Validators.required],
    password: ['', Validators.required],
    repeatPassword: ['', Validators.required]
  })

  constructor(private fb: FormBuilder,
    private router: Router,
    private cs: UseradminService) { }
    
    ngOnInit(): void {
    }
  
    onSubmit() {
      this.cs.register(new Useradmin(
        this.useradmin.value.firstName,
        this.useradmin.value.surname,
        this.useradmin.value.email,
        this.useradmin.value.username,
        this.useradmin.value.password
        ))
      this.router.navigate([''])
    }
  }

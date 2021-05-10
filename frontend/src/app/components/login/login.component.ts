import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from 'src/app/classes/login/login';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  //Password hashed send
  login = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  })

  constructor(private fb: FormBuilder,
    private router: Router,
    private ls: LoginService) { }

  ngOnInit(): void {
  }

  //onSubmit check user and user admin repository if user exists with userdetails
  onSubmit() {
    //encode password here or in login service?
    this.ls.login(new Login(
      this.login.value.username,
      this.login.value.password))
    //navigate to the environment page?
    this.router.navigate(['environments'])
  }

}

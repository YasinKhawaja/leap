import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from 'src/app/classes/login/login';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import { LoginService } from 'src/app/services/login/login.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

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
    private ls: LoginService,
    private jwt: JwtService) { }

  ngOnInit(): void {
  }

  //onSubmit check user and user admin repository if user exists with userdetails
  onSubmit() {
    //encode password here
    this.ls.login(new Login(this.login.value.username, this.login.value.password))
      .subscribe(
        (data: HttpResponse<any>) => {
          var token = data.headers.get("authorization").replace('Bearer ', '');
          this.jwt.storeJWT(token);
          this.jwt.loggedin(this.login.value.username);
      },
      error => {
        console.log(error)
      });
    }
}

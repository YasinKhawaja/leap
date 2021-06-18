import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Login } from 'src/app/classes/login/login';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import { LoginService } from 'src/app/services/login/login.service';
import Swal from 'sweetalert2';
import sha256 from 'crypto-js/sha256';
import { Router } from '@angular/router';

const salt = "!sH@2.5.6?.-_#eNc0.d3Ds@L.t";

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
    private ls: LoginService,
    private jwt: JwtService,
    private router: Router) { }

  ngOnInit(): void {
    if (this.jwt.userstatus.getValue()) {
      this.router.navigate(['/environments'])
    }
  }

  onSubmit() {
    var password = sha256(this.login.value.password + salt);
    this.ls.login(new Login(this.login.value.username, password))
      .subscribe(
        () => {
          this.jwt.getNewJwt(this.login.value.username)
          this.jwt.setRole()
          this.jwt.setUsername()
          this.jwt.loggedin(this.login.value.username);
          this.jwt.setUserIdle(true);
          this.jwt.tokenRefresh();
        },
        (error: HttpErrorResponse) => {
          Swal.fire('Error', error.error, 'error')
        });
  }
}

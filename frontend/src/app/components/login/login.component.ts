import { HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Login } from 'src/app/classes/login/login';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import { LoginService } from 'src/app/services/login/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  //Password hashed send
  login = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  })

  constructor(private fb: FormBuilder,
    private ls: LoginService,
    private jwt: JwtService) { }

  //onSubmit check user and user admin repository if user exists with userdetails
  onSubmit() {
    //encode password here
    this.ls.login(new Login(this.login.value.username, this.login.value.password))
      .subscribe(
        (data: HttpResponse<any>) => {
          var token = data.headers.get("authorization").replace('Bearer ', '');
          this.jwt.storeJWT(token);
          this.jwt.loggedin(this.login.value.username);
          this.jwt.setUserIdle(true);
          this.jwt.tokenRefresh();
      },
      error => {
        Swal.fire('Error', error.error.message, 'error')
      });
    }
}

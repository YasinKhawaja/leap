import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from 'src/app/classes/login/login';
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
    private ls: LoginService,private ns : NavbarService) { }

  ngOnInit(): void {
  }

  //tijdelijk tot login werkt
  deselect(): void{
    this.ns.userLogin();
  }
  //

  //onSubmit check user and user admin repository if user exists with userdetails
  onSubmit() {
    //encode password here
    this.ls.login(new Login(
      this.login.value.username,
      this.login.value.password))

      //change it so the enviornments only gets called on succesful login
    this.router.navigate(['/environments'])
  }

}

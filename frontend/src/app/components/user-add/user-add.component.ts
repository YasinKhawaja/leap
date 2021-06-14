import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { User } from 'src/app/classes/user/user';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import { UserService } from 'src/app/services/user/user.service';

enum Role {
  ADMIN = "User-admin",
  BEWERKER = "Bewerker",
  LEZER = "Lezer"
}

@Component({
  selector: 'app-user-add',
  templateUrl: './user-add.component.html',
  styleUrls: ['./user-add.component.css']
})
export class UserAddComponent {

  eRole = Role;

  user = this.fb.group({
    firstname: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
    surname: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
    email: ['', [Validators.required, Validators.pattern("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$")]],
    username: ['', Validators.required],
    role: ['', Validators.required]
  })

  constructor(private fb: FormBuilder, private us: UserService, private jwt: JwtService) { }

  onSubmit() {
    this.us.addUser(this.jwt.checkCompany(), new User(
      this.user.value.firstname,
      this.user.value.surname,
      this.user.value.email,
      this.user.value.username,
      ' '
    ), (Object.values(Role).indexOf(this.user.value.role)).toString())
  }
}

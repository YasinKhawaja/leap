import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from 'src/app/classes/user/user';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import { UserService } from 'src/app/services/user/user.service';
import Swal from 'sweetalert2';

enum Role {
  ADMIN = "User-admin",
  EDITOR = "Editor",
  READER = "Reader"
}

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

  eRole = Role;
  user: FormGroup;
  username: string
  sameuser: boolean = false

  constructor(private router: Router, private us: UserService, private fb: FormBuilder, private jwt: JwtService) { }

  ngOnInit(): void {
    this.getUser()
      .subscribe(
        result => {
          this.user = this.fb.group({
            firstname: [result.firstname, [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
            surname: [result.surname, [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
            email: [result.email, [Validators.required, Validators.pattern("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$")]],
            username: [result.username, Validators.required],
            role: [Object.values(Role)[result.role], Validators.required]
          })
          this.username = result.username
        },
        () => {
          Swal.fire('Error', 'Failed to load user details', 'error');
        }
      );
  }

  private getUser(): Observable<User> {
    var userId = this.router.url.split('/')[2];
    return this.us.getUser(userId);
  }

  onSubmit() {
    if (this.jwt.getUsername() == this.username) {
      this.sameuser = true
    }
    this.us.updateUser(this.router.url.split('/')[2], new User(
      this.user.value.firstname,
      this.user.value.surname,
      this.user.value.email,
      this.user.value.username,
      ""
    ), (Object.values(Role).indexOf(this.user.value.role)).toString(), this.sameuser)
  }

}

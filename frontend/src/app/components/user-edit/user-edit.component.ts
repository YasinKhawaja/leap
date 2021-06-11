import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from 'src/app/classes/user/user';
import { UserService } from 'src/app/services/user/user.service';
import Swal from 'sweetalert2';

enum Role {
  ADMIN = "User-admin",
  BEWERKER = "Bewerker",
  LEZER = "Lezer"
}

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

  eRole = Role;
  user: FormGroup;

  constructor(private router: Router, private us: UserService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.getUser()
      .subscribe(
        result => {
          this.user = this.fb.group({
            firstName: [result.firstName, [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
            surname: [result.surname, [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
            email: [result.email, [Validators.required, Validators.pattern("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$")]],
            username: [result.username, Validators.required],
            role: [Object.values(Role)[result.role], Validators.required]
          })
        },
        error => {
          Swal.fire('Error', 'Failed to load user details', 'error');
        }
      );
  }

  private getUser(): Observable<User> {
    var userId = this.router.url.split('/')[2];
    return this.us.getUser(userId);
  }

  onSubmit() {
    this.us.updateUser(this.router.url.split('/')[2], new User(
      this.user.value.firstName,
      this.user.value.surname,
      this.user.value.email,
      this.user.value.username,
      ""
    ), (Object.values(Role).indexOf(this.user.value.role)).toString())
  }

}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ResetpasswordService } from 'src/app/services/resetpassword/resetpassword.service';

@Component({
  selector: 'app-resetpassword-confirm',
  templateUrl: './resetpassword-confirm.component.html',
  styleUrls: ['./resetpassword-confirm.component.css']
})
export class ResetpasswordConfirmComponent implements OnInit {

  resetpassword = this.fb.group({
    password: ['', [Validators.required, Validators.minLength(8)]],
    repeatPassword: ['', Validators.required]
  }, {
    validator: this.checkPasswords
  })

  constructor(private fb: FormBuilder, private rps: ResetpasswordService) { }

  ngOnInit(): void {
  }

  checkPasswords(group: FormGroup){
    const password = group.controls.password.value;
    const repeatPassword = group.controls.repeatPassword.value;

    return password === repeatPassword ? null : { different: true }
  }

  get f(){
    return this.resetpassword.controls;
  }

  onSubmit() {
    this.rps.resetPassword(this.resetpassword.value.password);
  }
}

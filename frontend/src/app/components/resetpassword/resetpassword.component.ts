import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ResetpasswordService } from 'src/app/services/resetpassword/resetpassword.service';

@Component({
  selector: 'app-resetpassword',
  templateUrl: './resetpassword.component.html',
  styleUrls: ['./resetpassword.component.css']
})
export class ResetpasswordComponent {

  resetpassword = this.fb.group({
    email: ['', [Validators.required, Validators.pattern("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$")]]
  })

  constructor(private fb: FormBuilder, private rps: ResetpasswordService) { }

  onSubmit(){
    this.rps.resetPasswordMail(this.resetpassword.value.email);
  }
}



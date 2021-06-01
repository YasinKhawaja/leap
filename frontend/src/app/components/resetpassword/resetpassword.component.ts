import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ResetpasswordService } from 'src/app/services/resetpassword/resetpassword.service';

@Component({
  selector: 'app-resetpassword',
  templateUrl: './resetpassword.component.html',
  styleUrls: ['./resetpassword.component.css']
})
export class ResetpasswordComponent implements OnInit {

  resetpassword = this.fb.group({
    email: ['', Validators.required]
  })

  constructor(private fb: FormBuilder, private rps: ResetpasswordService) { }

  ngOnInit(): void {
  }

  onSubmit(){
    this.rps.resetPassword(this.resetpassword.value.email);
  }
}



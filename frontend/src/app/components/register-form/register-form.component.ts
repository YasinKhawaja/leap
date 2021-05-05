import { Component, OnInit} from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Company } from 'src/app/classes/company/company';
import { CompanyService } from 'src/app/services/company/company.service';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit{
  
  company = this.fb.group({
    vatNumber: ['', Validators.required],
    companyName: ['', Validators.required],
    email: ['', Validators.email],
    streetName: ['', Validators.required],
    houseNumber: ['', Validators.required],
    postcode: ['', Validators.required],
    city: ['', Validators.required],
    country: ['', Validators.required],
    businessActivity: ['', Validators.nullValidator],
    taxOffice: ['', Validators.nullValidator]
  })

  constructor(private fb: FormBuilder,
    private router: Router,
    private cs: CompanyService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.cs.register(new Company(
      this.company.value.vatNumber,
      this.company.value.companyName,
      this.company.value.email,
      this.company.value.streetName,
      this.company.value.houseNumber,
      this.company.value.postcode,
      this.company.value.city,
      this.company.value.country,
      this.company.value.businessActivity,
      this.company.value.taxOffice))
    this.router.navigate([''])
  }
}

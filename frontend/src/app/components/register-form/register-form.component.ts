import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Company } from 'src/app/classes/company/company';
import { CompanyService } from 'src/app/services/company/company.service';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent{

  company = this.fb.group({
    vatNumber: ['', [Validators.required, Validators.pattern('^[A-Za-z0-9 ]+$')]],
    companyName: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
    email: ['', [Validators.required, Validators.pattern("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$")]],
    streetName: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
    houseNumber: ['', [Validators.required, Validators.pattern("^[0-9]*$")]],
    postcode: ['', [Validators.required, Validators.pattern("^[0-9]*$")]],
    city: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
    country: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
    businessActivity: ['', [Validators.nullValidator, Validators.pattern('[a-zA-Z ]+')]],
    taxOffice: ['', [Validators.nullValidator, Validators.pattern('[a-zA-Z ]+')]]
  })

  constructor(private fb: FormBuilder,
    private cs: CompanyService) { }

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
  }
}

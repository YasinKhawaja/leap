import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Itapplication } from 'src/app/classes/itapplication/itapplication';
import { ItapplicationService } from 'src/app/services/itapplication/itapplication.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

enum Currency{
  EUR = "EUR",
  GBP = "GBP",
  USD = "USD"
}

//voluit schrijven
enum TIME{
  TOLERATE = "Tolerate",
  INVEST = "Invest",
  ELIMINATE = "Eliminate",
  MIGRATE = "Migrate"
}

@Component({
  selector: 'app-itapplication-add',
  templateUrl: './itapplication-add.component.html',
  styleUrls: ['./itapplication-add.component.css']
})
export class ItapplicationAddComponent implements OnInit {

  ecostCurrency = Currency;
  etimeValue = TIME;

  itapplication = this.fb.group({
    name: ['', Validators.required],
    technology: ['', Validators.required],
    version: ['', Validators.required],
    acquisitionDate: ['', Validators.required],
    endOfLife: ['', Validators.required],
    currentScalability: ['', [Validators.required, Validators.pattern('[1-5]')]],
    expectedScalability: ['', [Validators.required, Validators.pattern('[1-5]')]],
    currentPerformance: ['', [Validators.required, Validators.pattern('[1-5]')]],
    expectedPerformance: ['', [Validators.required, Validators.pattern('[1-5]')]],
    currentSecurityLevel: ['', [Validators.required, Validators.pattern('[1-5]')]],
    expectedSecurityLevel: ['', [Validators.required, Validators.pattern('[1-5]')]],
    currentStability: ['', [Validators.required, Validators.pattern('[1-5]')]],
    expectedStability: ['', [Validators.required, Validators.pattern('[1-5]')]],
    costCurrency:['', Validators.required],
    currentValueForMoney: ['', [Validators.required, Validators.pattern('[1-5]')]],
    currentTotalCostPerYear: ['', Validators.required],
    toleratedTotalCostPerYear: ['', Validators.required],
    timeValue: ['', Validators.required]
  });

  constructor(private fb: FormBuilder, private its: ItapplicationService, private ns: NavbarService) { }

  ngOnInit(): void {
  }

  onSubmit(){
    let environmentId = this.ns.getEnvironment();

    var newITApplication = new Itapplication(
      this.itapplication.value.name,
      this.itapplication.value.technology,
      this.itapplication.value.version,
      this.itapplication.value.acquisitionDate,
      this.itapplication.value.endOfLife,
      this.itapplication.value.currentScalability,
      this.itapplication.value.expectedScalability,
      this.itapplication.value.currentPerformance,
      this.itapplication.value.expectedPerformance,
      this.itapplication.value.currentSecurityLevel,
      this.itapplication.value.expectedSecurityLevel,
      this.itapplication.value.currentStability,
      this.itapplication.value.expectedStability,
      this.itapplication.value.costCurrency,
      this.itapplication.value.currentValueForMoney,
      this.itapplication.value.currentTotalCostPerYear,
      this.itapplication.value.toleratedTotalCostPerYear,
      this.itapplication.value.timeValue.toUpperCase()
    );

    this.its.createITApplication_CurrentEnvironment(environmentId, newITApplication);
  }

}

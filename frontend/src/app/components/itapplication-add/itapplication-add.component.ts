import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Itapplication } from 'src/app/classes/itapplication/itapplication';
import { ItapplicationService } from 'src/app/services/itapplication/itapplication.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

enum Currency{
  EUR = "EUR",
  GBP = "GBP",
  USD = "USD",
  A = " "
}

//voluit schrijven
enum TIME{
  TOLERATE = "Tolerate",
  INVEST = "Invest",
  ELIMINATE = "Eliminate",
  MIGRATE = "Migrate",
  A = " "
}

@Component({
  selector: 'app-itapplication-add',
  templateUrl: './itapplication-add.component.html',
  styleUrls: ['./itapplication-add.component.css']
})
export class ItapplicationAddComponent {

  ecostCurrency = Currency;
  etimeValue = TIME;

  itapplication = this.fb.group({
    name: ['', Validators.required],
    technology: ['', Validators.required],
    version: ['', Validators.required],
    acquisitionDate: ['', Validators.required],
    endOfLife: [' ', Validators.nullValidator],
    currentScalability: ['0', Validators.pattern('[0-5]')],
    expectedScalability: ['0', Validators.pattern('[0-5]')],
    currentPerformance: ['0', Validators.pattern('[0-5]')],
    expectedPerformance: ['0', Validators.pattern('[0-5]')],
    currentSecurityLevel: ['0', Validators.pattern('[0-5]')],
    expectedSecurityLevel: ['0', Validators.pattern('[0-5]')],
    currentStability: ['0', Validators.pattern('[0-5]')],
    expectedStability: ['0', Validators.pattern('[0-5]')],
    costCurrency:[' ', Validators.nullValidator],
    currentValueForMoney: ['0', Validators.pattern('[0-5]')],
    currentTotalCostPerYear: ['0', Validators.nullValidator],
    toleratedTotalCostPerYear: ['0', Validators.nullValidator],
    timeValue: [' ', Validators.nullValidator]
  });

  constructor(private fb: FormBuilder, private its: ItapplicationService, private ns: NavbarService) { }

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

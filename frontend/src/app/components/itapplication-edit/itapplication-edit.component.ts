import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Itapplication } from 'src/app/classes/itapplication/itapplication';
import { ItapplicationService } from 'src/app/services/itapplication/itapplication.service';

enum Currency {
  EUR = "EUR",
  GBP = "GBP",
  USD = "USD",
  A = " "
}

//voluit schrijven
enum TIME {
  TOLERATE = "Tolerate",
  INVEST = "Invest",
  ELIMINATE = "Eliminate",
  MIGRATE = "Migrate",
  A = " "
}

@Component({
  selector: 'app-itapplication-edit',
  templateUrl: './itapplication-edit.component.html',
  styleUrls: ['./itapplication-edit.component.css']
})
export class ItapplicationEditComponent implements OnInit {

  currentITApplication: Itapplication;
  itapplication: FormGroup;
  ecostCurrency = Currency
  etimeValue = TIME;

  constructor(private fb: FormBuilder, private router: Router, private its: ItapplicationService) { }

  ngOnInit(): void {
    this.getCurrentITApplication()
      .subscribe(
        result => {
          this.itapplication = this.fb.group({
            name: [result.name, Validators.required],
            technology: [result.technology, Validators.required],
            version: [result.version, Validators.required],
            acquisitionDate: [result.acquisitionDate, Validators.required],
            endOfLife: [result.endOfLife],
            currentScalability: [result.currentScalability, Validators.pattern('[0-5]')],
            expectedScalability: [result.expectedScalability, Validators.pattern('[0-5]')],
            currentPerformance: [result.currentPerformance, Validators.pattern('[0-5]')],
            expectedPerformance: [result.expectedPerformance, Validators.pattern('[0-5]')],
            currentSecurityLevel: [result.currentSecurityLevel, Validators.pattern('[0-5]')],
            expectedSecurityLevel: [result.expectedSecurityLevel, Validators.pattern('[0-5]')],
            currentStability: [result.currentStability, Validators.pattern('[0-5]')],
            expectedStability: [result.expectedStability, Validators.pattern('[0-5]')],
            costCurrency: [result.costCurrency],
            currentValueForMoney: [result.currentValueForMoney, Validators.pattern('[0-5]')],
            currentTotalCostPerYear: [result.currentTotalCostPerYear],
            toleratedTotalCostPerYear: [result.toleratedTotalCostPerYear],
            timeValue: [result.timeValue]
          });
        }
      );
  }

  private getCurrentITApplication(): Observable<Itapplication> {
    var itApplicationId = this.router.url.split('/')[2];
    return this.its.getITApplication(itApplicationId);
  }

  onSubmit() {
    let itApplicationId = this.router.url.split('/')[2];

    let updatedITApplication = new Itapplication(
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

    this.its.updateITApplication_CurrentEnvironment(itApplicationId, updatedITApplication);
  }

}

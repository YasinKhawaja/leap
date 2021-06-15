//import { HttpParams } from "@angular/common/http";

export class Itapplication {
    public id: number

    constructor(
        public name: string,
        public technology: string,
        public version: string,
        public acquisitionDate: string,
        public endOfLife: string,
        public currentScalability: string,
        public expectedScalability: string,
        public currentPerformance: string,
        public expectedPerformance: string,
        public currentSecurityLevel: string,
        public expectedSecurityLevel: string,
        public currentStability: string,
        public expectedStability: string,
        public costCurrency: string,
        public currentValueForMoney: string,
        public currentTotalCostPerYear: string,
        public toleratedTotalCostPerYear: string,
        public timeValue: string,
        public importanceFactor: string
    ) { }

    /*getParams(): HttpParams{
        return new HttpParams()
        .set('name', this.name)
        .set('technology', this.technology)
        .set('version', this.version)
        .set('acquisitionDate', this.acquisitionDate)
        .set('endOfLife', this.endOfLife)
        .set('currentScalability', this.currentScalability)
        .set('expectedScalability', this.expectedScalability)
        .set('currentPerformance', this.currentPerformance)
        .set('expectedPerformance', this.expectedPerformance)
        .set('currentSecurityLevel', this.currentSecurityLevel)
        .set('expectedSecurityLevel', this.expectedSecurityLevel)
        .set('currentStability', this.currentStability)
        .set('expectedStability', this.expectedStability)
        .set('costCurrency', this.costCurrency)
        .set('currentValueForMoney', this.currentValueForMoney)
        .set('currentTotalCostPerYear', this.currentTotalCostPerYear)
        .set('toleratedTotalCostPerYear', this.toleratedTotalCostPerYear)
        .set('timeValue', this.timeValue)
    }*/
}

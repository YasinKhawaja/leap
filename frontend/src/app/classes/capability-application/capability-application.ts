import { HttpParams } from "@angular/common/http";

export class CapabilityApplication {

    //add capability and itapplication or send id with router????

    public id: number

    constructor(
        public importance: string, //percentage -> number?
        public businessEfficiencySupport: string,
        public businessFunctionalCoverage: string,
        public businessCorrectness: string,
        public businessFuturePotential: string,
        public informationCompleteness: string,
        public informationCorrectness: string, 
        public informationAvailability: string
    ){}

    getParams(): HttpParams{
        return new HttpParams()
        .set('importance', this.importance)
        .set('businessEfficiencySupport', this.businessEfficiencySupport)
        .set('businessFunctionalCoverage', this.businessFunctionalCoverage)
        .set('businessFuturePotential', this.businessFuturePotential)
        .set('informationCompleteness', this.informationCompleteness)
        .set('informationCorrectness', this.informationCorrectness)
        .set('informationAvailability', this.informationAvailability)
    }
}

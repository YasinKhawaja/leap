import { NumberInput } from "@angular/cdk/coercion";
import { HttpParams } from "@angular/common/http";
import { Capability } from "../capability/capability";
import { Itapplication } from "../itapplication/itapplication";

export class CapabilityApplication {

    //add capability and itapplication or send id with router????

    public id: number
    public capability: Capability
    public itApplication: Itapplication
    public importance: number //percentage -> number?

    constructor(
        public application: string,
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
        .set('application', this.application)
        .set('businessEfficiencySupport', this.businessEfficiencySupport)
        .set('businessFunctionalCoverage', this.businessFunctionalCoverage)
        .set('businessCorrectness', this.businessCorrectness)
        .set('businessFuturePotential', this.businessFuturePotential)
        .set('informationCompleteness', this.informationCompleteness)
        .set('informationCorrectness', this.informationCorrectness)
        .set('informationAvailability', this.informationAvailability)
    }
}

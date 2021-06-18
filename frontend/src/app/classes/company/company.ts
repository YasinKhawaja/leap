import { HttpParams } from "@angular/common/http";

export class Company {

    public id: string;
    public approved: string;

    constructor(
        public vatNumber: string,
        public companyName: string,
        public email: string,
        public streetName: string,
        public houseNumber: string,
        public postcode: string,
        public city: string,
        public country: string,
        public businessActivity: string,
        public taxOffice: string
    ) { }

    getParams(): HttpParams {
        return new HttpParams()
            .set('vatNumber', this.vatNumber)
            .set('companyName', this.companyName)
            .set('email', this.email)
            .set('streetName', this.streetName)
            .set('houseNumber', this.houseNumber)
            .set('postcode', this.postcode)
            .set('city', this.city)
            .set('country', this.country)
            .set('businessActivity', this.businessActivity)
            .set('taxOffice', this.taxOffice);
    }
}

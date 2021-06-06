import { HttpParams } from "@angular/common/http";
import { Businessprocess } from "../businessprocess/businessprocess";

export class CapabilityBusinessprocess {

    public id: number
    public businessProcess: Businessprocess

    constructor(
        public process: string
    ){}

    getParams(): HttpParams{
        return new HttpParams()
        .set('businessprocess', this.process)
    }
}

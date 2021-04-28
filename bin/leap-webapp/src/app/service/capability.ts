import { HttpParams } from "@angular/common/http";

export class Capability {
    constructor(public name: string){}

    getParams() : HttpParams{
        return new HttpParams()
            .set('name', this.name);
    }
}
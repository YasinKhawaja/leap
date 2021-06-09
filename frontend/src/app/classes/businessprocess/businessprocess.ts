import { HttpParams } from "@angular/common/http";

export class Businessprocess {
    public id: string;
    constructor(
        public name: string,
        public description: string
    ) { }
}

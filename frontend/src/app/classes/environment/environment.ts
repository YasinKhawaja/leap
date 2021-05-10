import { HttpParams } from "@angular/common/http";

export class Environment {

    constructor(public name: string) {
    }

    getParams(): HttpParams {
        return new HttpParams().set('name', this.name);
    }

}
import { HttpParams } from "@angular/common/http";

export class Environment {

    public id: number;

    constructor(public name: string) {
        this.id = 1;
    }

    getParams(): HttpParams {
        return new HttpParams().set('name', this.name);
    }

}
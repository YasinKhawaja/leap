import { HttpParams } from "@angular/common/http";

export class Environment {

    public id: number;
    //public name: string;

    constructor(public name: string) {
        this.id = 0;
    }

    getParams(): HttpParams {
        return new HttpParams().set('name', this.name);
    }

}
import { HttpParams } from "@angular/common/http";

export class Environment {

    public id: number;

    constructor(public name: string) { 
        this.id = 0;
    }

    getId(): number {
        return this.id;
    }

    getParams(): HttpParams {
        return new HttpParams().set('name', this.name);
    }

}
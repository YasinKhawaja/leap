import { HttpParams } from "@angular/common/http";
import { Capability } from "../capability/capability";

export class Environment {

    public id: number
    public capabilities: Capability[]

    constructor(public name: string) {
    }

    getParams(): HttpParams {
        return new HttpParams().set('name', this.name);
    }

}
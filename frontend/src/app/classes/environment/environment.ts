import { Capability } from "../capability/capability";

export class Environment {

    public id: string;
    public capabilities: Capability[];

    constructor(public name: string) {
        this.id = '';
        this.capabilities = []
    }

}
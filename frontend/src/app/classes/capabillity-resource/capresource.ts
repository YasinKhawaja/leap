import { Capability } from "../capability/capability";
import { Resource } from "../resource/resource";

export class CapResource {

    public id: string;

    constructor(public capability: Capability, public resource: Resource, public numberOfResources: string) { }

}
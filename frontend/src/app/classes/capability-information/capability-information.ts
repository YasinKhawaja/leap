import { Capability } from "../capability/capability";
import { Information } from "../information/information";

export class CapabilityInformation {

    public id: string;

    constructor(
        public capability: Capability,
        public information: Information,
        public criticality: string) { }
}

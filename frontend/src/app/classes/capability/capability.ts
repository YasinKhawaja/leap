export class Capability {

    public id: string;
    public parent: string;
    public level: string;
    public informationQuality: string;
    public applicationFit: string;
    public subcapabilities: Capability[];

    constructor(
        public name: string,
        public paceOfChange: string,
        public targetOperationModel: string,
        public resourcesQuality: string
    ) { }

}
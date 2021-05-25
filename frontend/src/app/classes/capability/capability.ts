
export class Capability {

    public id: string
    public parentId: string
    public level: string
    public informationQuality: string
    public applicationFit: string

    constructor(public name: string, public paceOfChange: string, public targetOperationModel: string, public resourcesQuality: string) {
        this.id = '';
        this.parentId = '';
        this.level = '';
        this.informationQuality = '0';
        this.applicationFit = '0';
    }

}
import { HttpParams } from "@angular/common/http";

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

    getParams(): HttpParams {
        return new HttpParams()
            //.set('parentId', this.parentId)
            //.set('level', this.level)
            .set('name', this.name)
            .set('resourcesQuality', this.resourcesQuality)
            .set('paceOfChange', this.paceOfChange)
            .set('targetOperationModel', this.targetOperationModel)
            //.set('informationQuality', this.informationQuality)
            //.set('applicationFit', this.applicationFit);
    }
}
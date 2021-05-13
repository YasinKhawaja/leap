import { HttpParams } from "@angular/common/http";

export class Capability {

    public id: number
    public parentId: string // number
    public level: string // number
    public informationQuality: string // number
    public applicationFit: string // number

    constructor(public name: string, public paceOfChange: string, public tom: string, public resourcesQuality: string) {
        this.parentId = '';
        this.level = '';
        this.informationQuality = '';
        this.applicationFit = '';
    }

    getParams(): HttpParams {
        return new HttpParams()
            //.set('parentId', this.parentId)
            //.set('level', this.level)
            .set('name', this.name)
            .set('resourcesQuality', this.resourcesQuality)
            .set('paceOfChange', this.paceOfChange)
            .set('tom', this.tom)
            //.set('informationQuality', this.informationQuality)
            //.set('applicationFit', this.applicationFit);
    }
}
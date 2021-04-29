import { HttpParams } from "@angular/common/http";

export class Capability {
    public parentId: string
    public level: string
    public paceOfChange: string
    public tom: string
    public resourcesQuality: string
    public informationQuality: string
    public applicationFit: string
    

    constructor(public name: string){
        this.parentId = '';
        this.level = '';
        this.paceOfChange = '';
        this.tom = '';
        this.resourcesQuality = '';
        this.informationQuality = '';
        this.applicationFit = '';

    }

    getParams() : HttpParams{
        return new HttpParams()
            .set('name', this.name)
            .set('resourcesQuality', this.resourcesQuality)
            .set('parentId', this.parentId)
            .set('level', this.level)
            .set('paceOfChange', this.paceOfChange)
            .set('tom', this.tom)
            .set('informationQuality', this.informationQuality)
            .set('applicationFit', this.applicationFit)
    }
}
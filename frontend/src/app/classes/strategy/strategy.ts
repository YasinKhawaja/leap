import { HttpParams } from "@angular/common/http";

export class Strategy {

    public id: string

    constructor(public name: string , public timeframeFrom: string ,public timeframeTo: string ){
    this.id='';
    }

    getParams(): HttpParams {
        return new HttpParams().set('name', this.name)
        .set('timeframeFrom', this.timeframeFrom)
        .set('timeframeTo', this.timeframeTo);
    }

}
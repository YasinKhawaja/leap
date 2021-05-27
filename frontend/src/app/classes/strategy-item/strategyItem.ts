import { HttpParams } from "@angular/common/http";

export class StrategyItem {

    public id: string

    constructor(public name: string , public description: string){
        this.id = '';
    }

    getParams(): HttpParams {
        return new HttpParams().set('name', this.name)
        .set('description', this.description)
      
    }

}
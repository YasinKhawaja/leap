import { HttpParams } from "@angular/common/http";

export class Project {
    public id: string;
    constructor(
        public name: string,
        public description: string
    ){}

    getParams(): HttpParams{
        return new HttpParams()
        .set('name', this.name)
        .set('description', this.description)
    }
}

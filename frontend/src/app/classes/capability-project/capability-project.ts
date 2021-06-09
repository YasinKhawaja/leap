import { HttpParams } from "@angular/common/http"
import { Project } from "../project/project"

export class CapabilityProject {

    public id: number
    public project: Project

    constructor(
        public projectname: string
    ){}

    getParams(): HttpParams{
        return new HttpParams()
        .set('projectname', this.projectname)
    }
}
import { HttpParams } from "@angular/common/http";

export class Login {
    constructor(
        public username: string,
        public password: string,
    ){}

    getParams() : HttpParams{
        return new HttpParams()
            .set('username', this.username)
            //encrypt password
            .set('password', this.password)
    }
}

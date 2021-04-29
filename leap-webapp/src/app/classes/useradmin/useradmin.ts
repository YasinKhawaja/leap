import { HttpParams } from "@angular/common/http"    

    export class Useradmin {
        constructor(
            public firstName: string,
            public surname: string,
            public email: string,
            public username: string,
            public password: string,
            ){}
    
            getParams() : HttpParams{
                return new HttpParams()
                    .set('firstName', this.firstName)
                    .set('surname', this.surname)
                    .set('email', this.email)
                    .set('username', this.username)
                    .set('password', this.password)
            }
}

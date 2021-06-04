import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { NavbarService } from "../navbar/navbar.service"

@Injectable()
export class AuthInterceptor implements HttpInterceptor{

    constructor(private ns: NavbarService){}

    intercept(req: HttpRequest<any>,
        next: HttpHandler): Observable<HttpEvent<any>> {
            const token = this.ns.readCookie("jwt");

            if(token) {
                //clones http headers and adds the authorization header
                const cloned = req.clone({
                    headers: req.headers.set("Authorization", 
                    "Bearer " + token),
                    withCredentials:true
                });
                return next.handle(cloned);
            }
            else {
                return next.handle(req);
            }
        }
}

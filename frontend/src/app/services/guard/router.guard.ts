import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';

import { JwtService } from '../jwt/jwt.service'

@Injectable({
  providedIn: 'root'
})
export class RouterGuard implements CanActivate {

  constructor(private auth: JwtService, private router: Router) {}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if (this.auth.getUserStatus()){
        if(this.auth.validateJWT()){
          return true
        } else {
          this.auth.logout();
          return false;
        }
      }
      else{
        this.router.navigate([`login`])
        return false;
      }
  }
  
}

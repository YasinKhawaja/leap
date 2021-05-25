import { stringify } from '@angular/compiler/src/util';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NavbarService {
  environmentSelected: BehaviorSubject<boolean> = new BehaviorSubject(false);
  environmentID: string;

  
  //tijdelijk tot inlog werkt 
  user:BehaviorSubject<boolean> = new BehaviorSubject(false);
  public userLogin(){
    this.user.next(true)
  }
  public userLogout(){
    this.user.next(false)
  } 
//


  public environmentStatus(): BehaviorSubject<boolean> {
    this.environmentSelected.next(JSON.parse(this.readCookie("Selected")));
    return this.environmentSelected;
  }

  public environmentSelect() {
    this.environmentSelected.next(true);
    this.createCookie("Selected", this.environmentSelected.value.toString(), 1)
  }
 
  public environmentDeselect() {
    this.environmentSelected.next(false);
    this.eraseCookie("Selected");
    this.eraseCookie("Environment");
  }

  public setEnvironment(environmentID: string) {
    this.createCookie("Environment", environmentID, 1);
  }

  public getEnvironment(): string{
    if(this.environmentSelected) {
      return this.readCookie("Environment");
    }
    else{
      return "no environment was selected"
    }
  }

  createCookie(name: string, value:string, days: number) {
    var expires = '';
    if (days) {
      var date = new Date();
      date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
      expires = "; expires=" + date.toUTCString();
    }
    document.cookie = `${name}=${value}${expires};path=/`;
  }

  readCookie(name: string) {

    var nameCK = `${name}=`;
    var cookieValues = document.cookie.split(';');

    for (var i = 0; i < cookieValues.length; i++) {
      var cookie = cookieValues[i];

      while (cookie.charAt(0) == ' ') {
        cookie = cookie.substring(1, cookie.length);
      }
      if (cookie.indexOf(nameCK) == 0){
        return cookie.substring(nameCK.length, cookie.length);
      } 
    }
    return null;
  }

  eraseCookie(name: string) {
    this.createCookie(name, "", -1);
  }
}

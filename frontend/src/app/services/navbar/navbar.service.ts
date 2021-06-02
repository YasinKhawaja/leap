import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NavbarService {
  environmentSelected: BehaviorSubject<boolean> = new BehaviorSubject(false);
  environmentID: string;
  environmentName: string;


  public environmentStatus(): BehaviorSubject<boolean> {
    if(this.readCookie("Environment") != undefined){
      this.environmentSelected.next(true);
    }
    return this.environmentSelected;
  }

  public environmentSelect() {
    this.environmentSelected.next(true);
  
  }

  public environmentDeselect() {
    this.environmentSelected.next(false);
    this.eraseCookie("Environment");
    this.eraseCookie("EnvironmentName")
  }

  public setEnvironment(environmentID: string) {
    this.createCookie("Environment", environmentID, 1);
  }

  public getEnvironment(): string {
    if (this.environmentSelected || this.readCookie("Environment") != "") {
      return this.readCookie("Environment");
    }
    else {
      return "no environment was selected"
    }
  }

  public setEnvironmentName(environmentName: string) {
    this.environmentName = environmentName;
    this.createCookie("EnvironmentName", this.environmentName, 1);
  }

  public getEnvironmentName():string {
    this.environmentName = this.readCookie("EnvironmentName");
    if(this.environmentName == undefined){
      return "Environment";
    }else{
      return this.environmentName;
    }
  }

  public setCapability(capabilityID: string) {
    this.createCookie("Capability", capabilityID, 1);
  }

  public getCapability(): string{
    return this.readCookie("Capability");
  }

  createCookie(name: string, value:string, days: number) {
    var expires = '';
    if (days) {
      var date = new Date();
      date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
      expires = "; expires=" + date.toUTCString();
    }
    if(value != ''){
      value = btoa(value);
    }
    document.cookie = `${btoa(name)}=${value}${expires};path=/`;
  }

  readCookie(name: string) {

    var nameCK = `${btoa(name)}=`;
    var cookieValues = document.cookie.split(';');

    for (var i = 0; i < cookieValues.length; i++) {
      var cookie = cookieValues[i];

      while (cookie.charAt(0) == ' ') {
        cookie = cookie.substring(1, cookie.length);
      }
      if (cookie.indexOf(nameCK) == 0) {
        console.log(atob(cookie.substring(nameCK.length, cookie.length)));
        return atob(cookie.substring(nameCK.length, cookie.length));
      }
    }
    return null;
  }

  eraseCookie(name: string) {
    this.createCookie(btoa(name), "", -1);
  }
}

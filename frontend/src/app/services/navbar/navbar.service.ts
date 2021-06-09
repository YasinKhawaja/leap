import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NavbarService {

  environmentSelected: BehaviorSubject<boolean> = new BehaviorSubject(false);
  environmentID: string;
  environmentName: string;

  strItemSelected: BehaviorSubject<boolean> = new BehaviorSubject(false);
  strItemID: string;
  strItemName: string;



  public environmentStatus(): BehaviorSubject<boolean> {
    if (this.readCookie("Environment") != undefined) {
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
    this.eraseCookie("EnvironmentName");
    this.eraseCookie("Capability")
  }

  public setEnvironmentCookie(environmentID: string) {
    this.createCookie("Environment", environmentID, 1);
  }

  public getEnvironmentCookie(): string {
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

  public getEnvironmentName(): string {
    this.environmentName = this.readCookie("EnvironmentName");
    if (this.environmentName == undefined) {
      return "Environment";
    } else {
      return this.environmentName;
    }
  }

  public setCapabilityCookie(capabilityID: string) {
    this.createCookie("Capability", capabilityID, 1);
  }

  public getCapabilityCookie(): string {
    return this.readCookie("Capability");
  }

  public setProgramCookie(programid: string) {
    this.createCookie("Program", programid, 1)
  }

  public getProgramCookie(): string {
    return this.readCookie("Program")
  }

  createCookie(name: string, value: string, days: number) {
    var expires = '';
    if (days) {
      var date = new Date();
      date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
      expires = "; expires=" + date.toUTCString();
    }
    if (value != '') {
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
        return atob(cookie.substring(nameCK.length, cookie.length));
      }
    }
    return null;
  }

  getCsrf(){
    var nameCK = `XSRF-TOKEN=`;
    var cookieValues = document.cookie.split(';');

    for (var i = 0; i < cookieValues.length; i++) {
      var cookie = cookieValues[i];

      while (cookie.charAt(0) == ' ') {
        cookie = cookie.substring(1, cookie.length);
      }
      if (cookie.indexOf(nameCK) == 0) {
        return cookie.substring(nameCK.length, cookie.length);
      }
    }
    return null;
  }

  eraseCookie(name: string) {
    this.createCookie((name), "", -1);
  }

  // To SET the res cookie
  setResourceCookie(resourceID: string) {
    this.createCookie("Resource", resourceID, 1);
  }

  // To GET the res cookie
  getResourceCookie(): string {
    return this.readCookie("Resource");
  }


 //

 public strategyItemStatus(): BehaviorSubject<boolean> {
  this.strItemSelected.next(JSON.parse(this.readCookie("Selected")));
  return this.strItemSelected;
}

public strategyItemSelect() {
  this.strItemSelected.next(true);
  this.createCookie("Selected", this.strItemSelected.value.toString(), 1)
}

public strategyItemDeselect() {
  this.strItemSelected.next(false);
  this.eraseCookie("Selected");
  this.eraseCookie("StrategyItem");
}

 public setStrategyItem(strategyItemId: string) {
  this.createCookie("StrategyItem", strategyItemId, 1);
}

public getStrategyItem(): string{
  if(this.strItemSelected) {
    return this.readCookie("StrategyItem");
  }
  else {
    return "no strategy Item was selected"
  }
}

}

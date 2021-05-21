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

public environmentSelect() {
  this.environmentSelected.next(true);
}

  public environmentDeselect() {
    this.environmentSelected.next(false);
  }

  public setEnvironment(environmentID: string) {
    this.environmentID = environmentID;
  }

  public getEnvironment(): string{
    if(this.environmentSelected) {
      return this.environmentID;
    }
    else{
      return "no environment was selected"
    }
  }
}

import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { StrategyService } from 'src/app/services/strategy/strategy.service';
import { Location } from '@angular/common';
import { Strategy } from 'src/app/classes/strategy/strategy';
import { StrategyComponent } from '../strategy/strategy.component';

@Component({
  selector: 'app-strategy-delete',
  templateUrl: './strategy-delete.component.html',
  styleUrls: ['./strategy-delete.component.css']
})
export class StrategyDeleteComponent implements OnInit {
  
  str: Strategy;
  @Input() strCurrentValues: Strategy;

  constructor(private ss: StrategyService,
             private sc: StrategyComponent ) {

       
  }

  hide(): void {
    this.sc.hideAll();
  } 
  ngOnInit(): void {
  }

  deleteStrategyFromEnvironment() {

    this.ss.deleteStrategy_CurrentEnvironment(this.strCurrentValues.id)
    .subscribe(
      () => {
        this.sc.ngOnInit()
        this.sc.hideAll()
      }
    )
   

  }

 
}

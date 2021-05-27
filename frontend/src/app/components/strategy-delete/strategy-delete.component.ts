import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { StrategyService } from 'src/app/services/strategy/strategy.service';
import { Location } from '@angular/common';
import { Strategy } from 'src/app/classes/strategy/strategy';

@Component({
  selector: 'app-strategy-delete',
  templateUrl: './strategy-delete.component.html',
  styleUrls: ['./strategy-delete.component.css']
})
export class StrategyDeleteComponent implements OnInit {
  
  str: Strategy;

  constructor(private ss: StrategyService,
              private router: Router, private location: Location) {

       
  }

  ngOnInit(): void {
  }

  deleteStrategyFromEnvironment() {

    let strId = this.router.url.split('/')[2]; 
    this.ss.deleteStrategy_CurrentEnvironment(strId);

    this.navigateBack();
  }

  navigateBack() {
  this.location.back();
  }
}

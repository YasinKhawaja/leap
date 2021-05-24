import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { StrategyService } from 'src/app/services/strategy/strategy.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-strategy-delete',
  templateUrl: './strategy-delete.component.html',
  styleUrls: ['./strategy-delete.component.css']
})
export class StrategyDeleteComponent implements OnInit {
  

  constructor(private ss: StrategyService,
              private router: Router, private location: Location) {

       
  }

  ngOnInit(): void {
  }

  deleteStrategyFromEnvironment() {
    //var envId = this.router.url.split('/')[2];
    //var straIdToDelete = this.router.url.split('/')[4];

    let strId = this.router.url.split('/')[2]; 
    this.ss.deleteStrategy_CurrentEnvironment(strId);

    this.navigateBack();
  }

  navigateBack() {
  this.location.back();
  }
}

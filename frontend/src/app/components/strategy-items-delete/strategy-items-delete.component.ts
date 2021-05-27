import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StrategyItem } from 'src/app/classes/strategy-item/strategyItem';
import { StrategyItemService } from 'src/app/services/strategy-item/strategy-item.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-strategy-items-delete',
  templateUrl: './strategy-items-delete.component.html',
  styleUrls: ['./strategy-items-delete.component.css']
})
export class StrategyItemsDeleteComponent implements OnInit {

  strId: string;
  strItemId: string;

  strItem: StrategyItem;

  constructor(private si: StrategyItemService, private router: Router, 
  private location : Location
    ) {
    this.strId = this.router.url.split('/')[2];
    this.strItemId = this.router.url.split('/')[4];
  }

  ngOnInit(): void {
    this.si.getStrategyItem(this.strId, this.strItemId)
      .subscribe(
        res => this.strItem = res,
        err => console.log(err)
      );
  }

  
  deleteStrategyItem() {
    this.si.deleteStrategyItem(this.strId, this.strItemId)
      .subscribe(
        res => this.navigateBack(),
        err => console.log(err)
      );
     
  }

 
  navigateBack() {
    this.location.back();
    
  }

}

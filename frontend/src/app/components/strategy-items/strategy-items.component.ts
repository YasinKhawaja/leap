import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StrategyItem } from 'src/app/classes/strategy-item/strategyItem';
import { StrategyItemService } from 'src/app/services/strategy-item/strategy-item.service';

@Component({
  selector: 'app-strategy-items',
  templateUrl: './strategy-items.component.html',
  styleUrls: ['./strategy-items.component.css']
})
export class StrategyItemsComponent implements OnInit {

  strategyItems: StrategyItem[]

  constructor(private cs: StrategyItemService , private router: Router) { 
    this.strategyItems = [];
  }

  ngOnInit(): void {

    //var strId = this.router.url.split('/')[2];
}

}

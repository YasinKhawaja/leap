import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StrategyItem } from 'src/app/classes/strategy-item/strategyItem';
import { StrategyItemService } from 'src/app/services/strategy-item/strategy-item.service';
import { StrategyItemsComponent } from '../strategy-items/strategy-items.component';

@Component({
  selector: 'app-strategy-items-delete',
  templateUrl: './strategy-items-delete.component.html',
  styleUrls: ['./strategy-items-delete.component.css']
})
export class StrategyItemsDeleteComponent implements OnInit {

  strId: string;
  strItemId: string;

  strItem: StrategyItem;

  @Input() strItemCurrentValues: StrategyItem;

  constructor(private si: StrategyItemService, private router: Router ,private sic : StrategyItemsComponent) {
    this.strId = this.router.url.split('/')[2];
   
  }

  ngOnInit(): void {
    this.si.getStrategyItem(this.strId, this.strItemCurrentValues.id)
      .subscribe(
        res => this.strItem = res,
        err => console.log(err)
      );
  }

  hide(): void {
    this.sic.hideAll();
  }

  deleteStrategyItem() {
    this.si.deleteStrategyItem(this.strId, this.strItemCurrentValues.id)
      .subscribe(
        response => {
          this.sic.ngOnInit();
          this.sic.hideAll();
        }
      ); 
  }
}

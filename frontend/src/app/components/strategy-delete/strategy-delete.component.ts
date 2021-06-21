import { Component, Input } from '@angular/core';
import { StrategyService } from 'src/app/services/strategy/strategy.service';
import { Strategy } from 'src/app/classes/strategy/strategy';
import { StrategyComponent } from '../strategy/strategy.component';

@Component({
  selector: 'app-strategy-delete',
  templateUrl: './strategy-delete.component.html',
  styleUrls: ['./strategy-delete.component.css']
})
export class StrategyDeleteComponent {
  
  str: Strategy;
  @Input() strCurrentValues: Strategy;

  constructor(private ss: StrategyService,
             private sc: StrategyComponent ) {  
  }

  hide(): void {
    this.sc.hideAll();
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

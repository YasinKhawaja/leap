import { Component, Input } from '@angular/core';
import { CapabilityStrategyItems } from 'src/app/classes/capability-strategyitems/capability-strategyitems';
import { CapabilityStrategyitemService } from 'src/app/services/capability-strategyitem/capability-strategyitem.service';
import { CapabilityStrategyitemsComponent } from '../capability-strategyitems/capability-strategyitems.component';

@Component({
  selector: 'app-capability-strategyitems-delete',
  templateUrl: './capability-strategyitems-delete.component.html',
  styleUrls: ['./capability-strategyitems-delete.component.css']
})
export class CapabilityStrategyitemsDeleteComponent  {

  @Input() capStrategyItemCurrentValues: CapabilityStrategyItems;
  constructor(private csi: CapabilityStrategyitemService,private csic : CapabilityStrategyitemsComponent ) { }

 
  deleteCapability_StrategyItem() {
    
    this.csi.deleteCapabilityStrategyItem(this.capStrategyItemCurrentValues.id.toString())
    .subscribe(
      () => {
        this.csic.ngOnInit()
        this.csic.hideAll()
      }
    )

  }

  hide(): void {
    this.csic.hideAll();
  }

}

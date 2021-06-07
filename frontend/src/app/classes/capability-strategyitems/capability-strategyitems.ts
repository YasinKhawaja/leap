import { HttpParams } from "@angular/common/http";
import { Capability } from "../capability/capability";
import { StrategyItem } from "../strategy-item/strategyItem";

export class CapabilityStrategyItems
 {

    public id: number
    public capability: Capability
    public strategyItem: StrategyItem
  
    constructor(
        public strategyItemName: string,
        public strategicEmphasis: string
    ){}

    getParams(): HttpParams{
        return new HttpParams()
        .set('strategyItemName', this.strategyItemName)
        .set('strategicEmphasis', this.strategicEmphasis)
     
    }
}
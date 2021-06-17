import { Component, Input } from '@angular/core';
import { BusinessprocessService } from 'src/app/services/businessprocess/businessprocess.service';
import { Businessprocess } from 'src/app/classes/businessprocess/businessprocess';
import { BusinessprocessComponent } from '../businessprocess/businessprocess.component';

@Component({
  selector: 'app-businessprocess-delete',
  templateUrl: './businessprocess-delete.component.html',
  styleUrls: ['./businessprocess-delete.component.css']
})
export class BusinessprocessDeleteComponent  {

  @Input() processCurrentValues: Businessprocess;
  
  constructor(private bps: BusinessprocessService, private bpc : BusinessprocessComponent) { }

 
  deleteBusinessProcess() {
   // var businessprocessid = this.router.url.split('/')[3];
    var businessprocessid = this.processCurrentValues.id;

    this.bps.deleteBusinessProcess(businessprocessid);
  }

  hide(): void {
    this.bpc.hideAll();
  }

}

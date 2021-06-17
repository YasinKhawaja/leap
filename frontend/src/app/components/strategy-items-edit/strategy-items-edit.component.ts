import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { StrategyItem } from 'src/app/classes/strategy-item/strategyItem';
import { StrategyItemService } from 'src/app/services/strategy-item/strategy-item.service';
import Swal from 'sweetalert2';
import { StrategyItemsComponent } from '../strategy-items/strategy-items.component';

@Component({
  selector: 'app-strategy-items-edit',
  templateUrl: './strategy-items-edit.component.html',
  styleUrls: ['./strategy-items-edit.component.css']
})
export class StrategyItemsEditComponent implements OnInit {

 strItemEditForm: FormGroup;
 @Input() strItemCurrentValues: StrategyItem;

 

 constructor(private fb: FormBuilder,
   private router: Router,
   private si: StrategyItemService, private stc : StrategyItemsComponent){}

   ngOnInit(): void {
     this.initializeForm();
   }

   private initializeForm() {
    this.strItemEditForm = this.fb.group({
     name: [this.strItemCurrentValues.name, [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
     description: [this.strItemCurrentValues.description, [Validators.nullValidator]]
   });
   }

 get name() {
   return this.strItemEditForm.get('name');
 }

 get description() {
   return this.strItemEditForm.get('description');
 }

 hide(): void {
  this.stc.hideAll();
}
 
 onSubmit() {

  var strItemIdToUpdate = this.strItemCurrentValues.id;
   var strId = this.router.url.split('/')[2];
  // var strItemIdToUpdate = this.router.url.split('/')[4];

   console.log(strId);
   var newStrategyItemValues = new StrategyItem(
     this.name.value,
     this.description.value
   )


   this.si.updateStrategyItem(strId, strItemIdToUpdate,newStrategyItemValues)
   .subscribe(
     res => {
     console.log(res);
     this.stc.ngOnInit();
          this.stc.hideAll();
     },
     err => console.error(err)
   );

   

 }

}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { StrategyItem } from 'src/app/classes/strategy-item/strategyItem';
import { StrategyItemService } from 'src/app/services/strategy-item/strategy-item.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-strategy-items-edit',
  templateUrl: './strategy-items-edit.component.html',
  styleUrls: ['./strategy-items-edit.component.css']
})
export class StrategyItemsEditComponent implements OnInit {

 // Form
 strItemEditForm: FormGroup;

 strForm = this.fb.group({
   name: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
   paceOfChange: ['', Validators.required],
   tom: ['', Validators.required],
   resourcesQuality: ['', [Validators.required, Validators.pattern('[1-5]')]]
 });

 constructor(private fb: FormBuilder,
   private router: Router,
   private si: StrategyItemService){}

   ngOnInit(): void {
     this.initializeForm();
   }

   private initializeForm() {
    this.strItemEditForm = this.fb.group({
     name: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
     description: ['', [Validators.required]]
   });
   }

    // Form GETTERS
 get name() {
   return this.strItemEditForm.get('name');
 }

 get description() {
   return this.strItemEditForm.get('description');
 }

 
 
 onSubmit() {
   var strId = this.router.url.split('/')[2];
   var strItemIdToUpdate = this.router.url.split('/')[4];

   console.log(strId);
   var newStrategyItemValues = new StrategyItem(
     this.name.value,
     this.description.value
   )


   this.si.updateStrategyItem(strId, strItemIdToUpdate,newStrategyItemValues)
   .subscribe(
     res => {
     console.log(res);
       Swal.fire('Updated', `Strategy item updated.`, 'success');
       this.strItemEditForm.reset();
       this.router.navigate([`strategies/${strId}/strategyItems`])
     },
     err => console.error(err)
   );

   

 }

}

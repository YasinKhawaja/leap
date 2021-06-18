import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { StrategyItem } from 'src/app/classes/strategy-item/strategyItem';
import { StrategyItemService } from 'src/app/services/strategy-item/strategy-item.service';
import Swal from 'sweetalert2';
import { StrategyItemsComponent } from '../strategy-items/strategy-items.component';

@Component({
  selector: 'app-strategy-items-add',
  templateUrl: './strategy-items-add.component.html',
  styleUrls: ['./strategy-items-add.component.css']
})
export class StrategyItemsAddComponent implements OnInit {

  strItemAddForm: FormGroup;


  constructor(private fb: FormBuilder,
    private router: Router,
    private si: StrategyItemService,private sic : StrategyItemsComponent){}

    ngOnInit(): void {
      this.initializeForm();
    }

    private initializeForm() {
     this.strItemAddForm = this.fb.group({
      name: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
      description: ['', [Validators.nullValidator, Validators.pattern('^[A-Za-z0-9 ]+$')]]
    });
    }

  get name() {
    return this.strItemAddForm.get('name');
  }

  get description() {
    return this.strItemAddForm.get('description');
  }

  
  
  onSubmit() {
    var strId = this.router.url.split('/')[2];
    console.log(strId);
    var straItemToCreate = new StrategyItem(
      this.name.value,
      this.description.value
    )


    this.si.createStrategyItem(strId, straItemToCreate)
    .subscribe(
      res => {
        console.log(res);
        this.sic.ngOnInit();
        this.strItemAddForm.reset();
     
      },
      err => Swal.fire('Error', err.error.message, 'error')
    );
    
  }

  hide(): void {
    this.sic.hideAll();
  }
}

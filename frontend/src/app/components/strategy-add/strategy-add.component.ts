import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Strategy } from 'src/app/classes/strategy/strategy';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { StrategyService } from 'src/app/services/strategy/strategy.service';
import Swal from 'sweetalert2';
import { StrategyComponent } from '../strategy/strategy.component';

@Component({
  selector: 'app-strategy-add',
  templateUrl: './strategy-add.component.html',
  styleUrls: ['./strategy-add.component.css']
})
export class StrategyAddComponent implements OnInit {

  strAddForm: FormGroup;

  ngOnInit(): void {
    this.initializeForm();
  }

  private initializeForm() {
    this.strAddForm = this.fb.group({
      name: ['', [Validators.required, Validators.pattern('[a-zA-Z 0-9]+')]],
      timeframeFrom: ['', [Validators.required]],
      timeframeTo: ['', [Validators.required]]
    });
  }

  constructor(private fb: FormBuilder,
    private ss: StrategyService, private ns: NavbarService, private sc: StrategyComponent) { }


  get name() {
    return this.strAddForm.get('name');
  }

  get timeframeFrom() {
    return this.strAddForm.get('timeframeFrom');
  }

  get timeframeTo() {
    return this.strAddForm.get('timeframeTo');
  }



  onSubmit() {
    let envId = this.ns.getEnvironmentCookie();
    var straToCreate = new Strategy(
      this.name.value,
      this.timeframeFrom.value,
      this.timeframeTo.value
    )


    this.ss.createStrategy(envId, straToCreate)
      .subscribe(
        () => {
          this.sc.ngOnInit();
        },
        () => {
          Swal.fire('Error', `Failed to add strategy`, 'error')
        }
      )
    this.strAddForm.reset();


  }

  hide(): void {
    this.sc.hideAll();
  }

}

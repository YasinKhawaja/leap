import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Strategy } from 'src/app/classes/strategy/strategy';
import { StrategyService } from 'src/app/services/strategy/strategy.service';
import Swal from 'sweetalert2';
import { StrategyComponent } from '../strategy/strategy.component';

@Component({
  selector: 'app-strategy-edit',
  templateUrl: './strategy-edit.component.html',
  styleUrls: ['./strategy-edit.component.css']
})
export class StrategyEditComponent implements OnInit {

  strEditForm : FormGroup;
  strategyName: string
  @Input() strCurrentValues: Strategy;

  strategyForm = this.fb.group({
    name: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
    timeframeFrom: ['', [Validators.required]],
    timeframeTo: ['', [Validators.required]]
  })

  constructor(private cs: StrategyService,
    private fb: FormBuilder,private sc: StrategyComponent) {
  }

  ngOnInit(): void {
    this.initializeForm();
  }

  hide(): void {
    this.sc.hideAll();
  }
  initializeForm() {
  
  this.strEditForm = this.fb.group({
    name: [this.strCurrentValues.name, [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
    timeframeFrom: [this.strCurrentValues.timeframeFrom, [Validators.required]],
    timeframeTo: [this.strCurrentValues.timeframeTo, [Validators.required]]
  })
  }

  onSubmit() {
    var newStraValues = new Strategy(
      this.strEditForm.value.name,
      this.strEditForm.value.timeframeFrom,
      this.strEditForm.value.timeframeTo
    );

    this.cs.updateStrategy_CurrentEnvironment(this.strCurrentValues.id, newStraValues)
    .subscribe(
      () => {
        this.sc.ngOnInit()
        this.sc.hideAll()
      },
      () => Swal.fire('Error', `Failed to edit strategy`, `error`)
    )

    }
  }

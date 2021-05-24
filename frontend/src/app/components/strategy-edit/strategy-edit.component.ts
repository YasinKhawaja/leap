import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Strategy } from 'src/app/classes/strategy/strategy';
import { StrategyService } from 'src/app/services/strategy/strategy.service';

@Component({
  selector: 'app-strategy-edit',
  templateUrl: './strategy-edit.component.html',
  styleUrls: ['./strategy-edit.component.css']
})
export class StrategyEditComponent implements OnInit {

  strategyName: string

  strategyForm = this.fb.group({
    name: ['', [Validators.required, Validators.pattern('[a-zA-Z ]+')]],
    timeframeFrom: ['', [Validators.required]],
    timeframeTo: ['', [Validators.required]]
  })

  constructor(private cs: StrategyService,
    private fb: FormBuilder,
    private router: Router,
    private activeroute: ActivatedRoute) {
  }

  ngOnInit(): void {
  }

  onSubmit() {
  //  var envId = this.router.url.split('/')[2];
   // var straIdToUpdate = this.router.url.split('/')[4];
   let strId = this.router.url.split('/')[2];


    var newStraValues = new Strategy(
      this.strategyForm.value.name,
      this.strategyForm.value.timeframeFrom,
      this.strategyForm.value.timeframeTo
    );

    this.cs.updateStrategy_CurrentEnvironment(strId, newStraValues);

    this.router.navigate([`strategies/`])
    }
  }

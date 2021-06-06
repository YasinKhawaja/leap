import { Component, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from "@angular/forms";
import { CapabilityService } from "../../services/capability/capability.service"
import { Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { StrategyService } from 'src/app/services/strategy/strategy.service';
import { Strategy } from 'src/app/classes/strategy/strategy';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

@Component({
  selector: 'app-strategy',
  templateUrl: './strategy.component.html',
  styleUrls: ['./strategy.component.css']
})
export class StrategyComponent implements OnInit {

  strategies: Strategy[]

  constructor(private cs: StrategyService , private router: Router,private ns: NavbarService) { 
    this.strategies = [];
  }

  ngOnInit(): void {

    //var envId = this.router.url.split('/')[2];
    this.ns.environmentSelect();
    var envId = this.ns.getEnvironmentCookie();

    this.cs.getAllStrategyInEnvironment(envId)
               .subscribe(res => { this.strategies = res;  console.log(res); }, 
                          error => { console.error(error) })
}

}

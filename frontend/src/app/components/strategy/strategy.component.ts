import { Component, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from "@angular/forms";
import { CapabilityService } from "../../services/capability/capability.service"
import { Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { StrategyService } from 'src/app/services/strategy/strategy.service';
import { Strategy } from 'src/app/classes/strategy/strategy';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import { HttpErrorResponse } from '@angular/common/http';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-strategy',
  templateUrl: './strategy.component.html',
  styleUrls: ['./strategy.component.css']
})
export class StrategyComponent implements OnInit {

  strategies: Strategy[]

  constructor(private cs: StrategyService, private router: Router, private ns: NavbarService, public jwt: JwtService) {
    this.strategies = [];
  }

  ngOnInit(): void {

    this.ns.environmentSelect();
    var envId = this.ns.getEnvironmentCookie();

    this.cs.getAllStrategyInEnvironment(envId)
      .subscribe(
        result => {
          this.strategies = result;
        },
        (error: HttpErrorResponse) => {
          Swal.fire('Error', error.error, 'error')
        });
  }

  showStrAdd: boolean = false;

  showStrEdit: boolean = false;

  showStrDelete: boolean = false;
  strCurrentValues: Strategy;
  show(component: string, strategy?: Strategy): void {
    switch (component) {
      case 'strategy-add':
        this.hideAll();
        this.showStrAdd = true;
        break;
      case 'strategy-edit':
        this.showStrAdd = false;
        this.showStrDelete = false;
        this.strCurrentValues = strategy;
        this.showStrEdit = !this.showStrEdit;
        break;
      case 'strategy-delete':
        this.showStrAdd = false;
        this.showStrEdit = false;
        this.strCurrentValues = strategy;
        this.showStrDelete = !this.showStrDelete;
        break;

      default:
        break;
    }
  }

  hideAll(): void {
    this.showStrAdd = false;
    this.showStrEdit = false;
    this.showStrDelete = false;
  }

}

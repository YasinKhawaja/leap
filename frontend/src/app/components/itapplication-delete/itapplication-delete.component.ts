import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ItapplicationService } from 'src/app/services/itapplication/itapplication.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

@Component({
  selector: 'app-itapplication-delete',
  templateUrl: './itapplication-delete.component.html',
  styleUrls: ['./itapplication-delete.component.css']
})
export class ItapplicationDeleteComponent implements OnInit {

  constructor(private its: ItapplicationService, private router: Router, private location: Location, private ns: NavbarService) { }

  ngOnInit(): void {
  }

  deleteITApplication_CurrentEnvironment() {
    //let environmentId = this.router.url.split('/')[2];
    let itApplicationId = this.router.url.split('/')[2];

    this.its.deleteITApplication_CurrentEnvironment(itApplicationId);

    //delete later
    this.navigateBack();
  }

  navigateBack() {
    this.location.back();
  }
}

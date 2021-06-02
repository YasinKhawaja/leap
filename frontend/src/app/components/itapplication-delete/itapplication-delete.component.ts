import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ItapplicationService } from 'src/app/services/itapplication/itapplication.service';

@Component({
  selector: 'app-itapplication-delete',
  templateUrl: './itapplication-delete.component.html',
  styleUrls: ['./itapplication-delete.component.css']
})
export class ItapplicationDeleteComponent {

  constructor(private its: ItapplicationService, private router: Router, private location: Location) { }

  deleteITApplication_CurrentEnvironment() {
    let itApplicationId = this.router.url.split('/')[2];

    this.its.deleteITApplication_CurrentEnvironment(itApplicationId);

    //delete later
    this.navigateBack();
  }

  navigateBack() {
    this.location.back();
  }
}

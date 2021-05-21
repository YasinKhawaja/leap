import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Itapplication } from 'src/app/classes/itapplication/itapplication';
import { ItapplicationService } from 'src/app/services/itapplication/itapplication.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

@Component({
  selector: 'app-itapplication',
  templateUrl: './itapplication.component.html',
  styleUrls: ['./itapplication.component.css']
})
export class ItapplicationComponent implements OnInit {

  itApplications: Itapplication[];

  constructor(private its: ItapplicationService, private router: Router, private ns: NavbarService) { }

  ngOnInit(): void {
    let environmentId = this.ns.getEnvironment();
    //let environmentId = this.router.url.split('/')[2];

    this.its.getITApplications_CurrentEnvironment(environmentId)
      .subscribe(result => {
        this.itApplications = result;
        console.log(result);
      },
      error => console.log(error));
  }

}

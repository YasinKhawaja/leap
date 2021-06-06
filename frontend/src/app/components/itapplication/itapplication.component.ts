import { Component, OnInit } from '@angular/core';
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

  constructor(private its: ItapplicationService, private ns: NavbarService) { }

  ngOnInit(): void {
    let environmentId = this.ns.getEnvironmentCookie();

    this.its.getITApplications_CurrentEnvironment(environmentId)
      .subscribe(result => {
        this.itApplications = result;
      },
        error => console.log(error));
  }

}

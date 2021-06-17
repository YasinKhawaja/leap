import { Component, OnInit } from '@angular/core';
import { Itapplication } from 'src/app/classes/itapplication/itapplication';
import { ItapplicationService } from 'src/app/services/itapplication/itapplication.service';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

@Component({
  selector: 'app-itapplication',
  templateUrl: './itapplication.component.html',
  styleUrls: ['./itapplication.component.css']
})
export class ItapplicationComponent implements OnInit {

  itApplications: Itapplication[];

  constructor(private its: ItapplicationService, private ns: NavbarService, public jwt: JwtService) { }

  ngOnInit(): void {
    let environmentId = this.ns.getEnvironmentCookie();

    this.its.getITApplications_CurrentEnvironment(environmentId)
      .subscribe(result => {
        this.itApplications = result;
      },
        error => console.log(error));
  }

  showItAdd: boolean = false;

  showItEdit: boolean = false;

  showItDelete : boolean = false;
  itCurrentValues: Itapplication;
  show(component: string, itapplication?: Itapplication): void {
    switch (component) {
      case 'itapplication-add':
        this.hideAll();
        // Show
        this.showItAdd = true;
        break;
      case 'itapplication-edit':
        // Hide
        this.showItAdd = false;
        // Show
        this.itCurrentValues = itapplication;
        this.showItEdit = !this.showItEdit;
        break;

        case 'itapplication-delete':
          // Hide
          this.showItAdd = false;
          this.showItEdit = false;
          // Show
          this.itCurrentValues = itapplication;
          this.showItDelete = !this.showItDelete;
          break;
 
      default:
        break;
    }
  }

  hideAll(): void {
    this.showItAdd = false;
    this.showItEdit = false;
    this.showItDelete = false;
  }

}

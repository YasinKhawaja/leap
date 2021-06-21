import { Location } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-environment-delete',
  templateUrl: './environment-delete.component.html',
  styleUrls: ['./environment-delete.component.css']
})
export class EnvironmentDeleteComponent {

  constructor(private es: EnvironmentService, private router: Router, private location: Location) { }

  deleteEnvironment(): void {
    var envIdToDelete = this.router.url.split('/')[2];

    this.es.deleteEnvironment(envIdToDelete)
      .subscribe(
        () => this.navigateBack(),
        (error: HttpErrorResponse) => {
          Swal.fire('Error', error.error, 'error')
        });
  }

  // To navigate to the previous page
  navigateBack() {
    this.location.back();
  }

}
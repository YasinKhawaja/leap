import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EnvironmentService } from 'src/app/services/environment/environment.service';

@Component({
  selector: 'app-environment-delete',
  templateUrl: './environment-delete.component.html',
  styleUrls: ['./environment-delete.component.css']
})
export class EnvironmentDeleteComponent implements OnInit {

  constructor(private es: EnvironmentService, private router: Router) { }

  ngOnInit(): void {
  }

  deleteEnvironment(): void {
    let id = parseInt(this.router.url.split('/')[2]);

    this.es.deleteEnvironment(id).subscribe(data => console.log(data));

    // works like refresh
    this.es.getAllEnvironments().subscribe();

    this.router.navigate(['/environments']);
  }

}
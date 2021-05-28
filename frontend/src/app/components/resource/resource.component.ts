import { Component, OnInit } from '@angular/core';
import { Resource } from 'src/app/classes/resource/resource';
import { ResourceService } from 'src/app/services/resource/resource.service';

@Component({
  selector: 'app-resource',
  templateUrl: './resource.component.html',
  styleUrls: ['./resource.component.css']
})
export class ResourceComponent implements OnInit {

  resources: Resource[];

  constructor(private rs: ResourceService) { }

  ngOnInit(): void {
    this.getAllResources();
  }

  getAllResources(): void {
    this.rs.getAllResources().subscribe(res => this.resources = res, err => console.error(err));
  }

}

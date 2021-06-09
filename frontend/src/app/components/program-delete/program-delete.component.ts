import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProgramService } from 'src/app/services/program/program.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-program-delete',
  templateUrl: './program-delete.component.html',
  styleUrls: ['./program-delete.component.css']
})
export class ProgramDeleteComponent {

  constructor(private ps: ProgramService, private router: Router, private location: Location) { }

  deleteProgram() {
    var programid = this.router.url.split('/')[3];
    this.ps.deleteProgram(programid)
  }

  navigateBack(){
    this.location.back();
  }
}

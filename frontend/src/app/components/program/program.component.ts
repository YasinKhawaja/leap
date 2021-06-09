import { Component, OnInit } from '@angular/core';
import { Program } from 'src/app/classes/program/program';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { ProgramService } from 'src/app/services/program/program.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-program',
  templateUrl: './program.component.html',
  styleUrls: ['./program.component.css']
})
export class ProgramComponent implements OnInit {

  programs: Program[];

  constructor(private ps: ProgramService, private ns: NavbarService) { }

  ngOnInit(): void {
    var environmentid = this.ns.getEnvironmentCookie()
    this.ps.getPrograms(environmentid)
      .subscribe(
        result => {
          this.programs = result;
        },
        () => {
          Swal.fire('Error', `Failed to load data for environment: ${environmentid}`, 'error')
        }
      )
  }

  setProgram(programid: string){
    this.ns.setProgramCookie(programid)
  }

}

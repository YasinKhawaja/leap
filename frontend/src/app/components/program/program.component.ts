import { Component, OnInit } from '@angular/core';
import { Program } from 'src/app/classes/program/program';
import { JwtService } from 'src/app/services/jwt/jwt.service';
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

  constructor(private ps: ProgramService, private ns: NavbarService, public jwt: JwtService) { }

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

  setProgram(programid: string) {
    this.ns.setProgramCookie(programid)
  }


  showProgramAdd: boolean = false;

  showProgramEdit: boolean = false;

  showProgramDelete: boolean = false;
  programCurrentValues: Program;
  show(component: string, program?: Program): void {
    switch (component) {
      case 'program-add':
        this.hideAll();
        // Show
        this.showProgramAdd = true;
        break;
      case 'program-edit':
        // Hide
        this.showProgramAdd = false;
        this.showProgramDelete = false;
        // Show
        this.programCurrentValues = program;
        this.showProgramEdit = !this.showProgramEdit;
        break;
        case 'program-delete':
          // Hide
          this.showProgramAdd = false;
          this.showProgramEdit = false;
          // Show
          this.programCurrentValues = program;
          this.showProgramDelete = !this.showProgramDelete;
          break;
 
      default:
        break;
    }
  }

  hideAll(): void {
    this.showProgramAdd = false;
    this.showProgramEdit = false;
    this.showProgramDelete= false;
  }

}

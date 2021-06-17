import { Component, Input } from '@angular/core';
import { ProgramService } from 'src/app/services/program/program.service';
import { Program } from 'src/app/classes/program/program';
import { ProgramComponent } from '../program/program.component';

@Component({
  selector: 'app-program-delete',
  templateUrl: './program-delete.component.html',
  styleUrls: ['./program-delete.component.css']
})
export class ProgramDeleteComponent {

  @Input() programCurrentValues: Program;

  constructor(private ps: ProgramService, private pc: ProgramComponent) { }

  deleteProgram() {
  //  var programid = this.router.url.split('/')[3];
  var programid = this.programCurrentValues.id;
    this.ps.deleteProgram(programid)
  }

  hide(): void {
    this.pc.hideAll();
  }
}

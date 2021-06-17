import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Program } from 'src/app/classes/program/program';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { ProgramService } from 'src/app/services/program/program.service';
import { ProgramComponent } from '../program/program.component';

@Component({
  selector: 'app-program-add',
  templateUrl: './program-add.component.html',
  styleUrls: ['./program-add.component.css']
})
export class ProgramAddComponent{

  program = this.fb.group({
    name: ['', Validators.required],
    description: ['', Validators.nullValidator]
  })

  constructor(private fb: FormBuilder, private ps: ProgramService, private ns: NavbarService, private pc: ProgramComponent) { }

  onSubmit(){
    var environmentid = this.ns.getEnvironmentCookie();

    var newProgram = new Program(
      this.program.value.name,
      this.program.value.description
    )

    this.ps.addProgram(environmentid, newProgram)
  }

  hide(): void {
    this.pc.hideAll();
  }
}

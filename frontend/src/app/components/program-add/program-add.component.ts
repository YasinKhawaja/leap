import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Program } from 'src/app/classes/program/program';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { ProgramService } from 'src/app/services/program/program.service';
import Swal from 'sweetalert2';
import { ProgramComponent } from '../program/program.component';

@Component({
  selector: 'app-program-add',
  templateUrl: './program-add.component.html',
  styleUrls: ['./program-add.component.css']
})
export class ProgramAddComponent implements OnInit {

  program: FormGroup;

  ngOnInit(): void {
    this.initializeForm();
  }
  private initializeForm() {
  this.program = this.fb.group({
    name: ['', Validators.required],
    description: ['', Validators.nullValidator]
  })
}

  constructor(private fb: FormBuilder, private ps: ProgramService, private ns: NavbarService, private pc: ProgramComponent) { }

  onSubmit(){
    var environmentid = this.ns.getEnvironmentCookie();

    var newProgram = new Program(
      this.program.value.name,
      this.program.value.description
    )

    this.ps.addProgram(environmentid, newProgram)
    .subscribe(
      () => {
        this.pc.ngOnInit();
      },
      () => {
        Swal.fire('Error', `Failed to add program`, 'error')
      }
    )

  }

  hide(): void {
    this.pc.hideAll();
  }
}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Program } from 'src/app/classes/program/program';
import { ProgramService } from 'src/app/services/program/program.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-program-edit',
  templateUrl: './program-edit.component.html',
  styleUrls: ['./program-edit.component.css']
})
export class ProgramEditComponent implements OnInit {

  programid: string
  program: FormGroup;

  constructor(private router: Router, private ps: ProgramService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.getProgram()
      .subscribe(
        result => {
          this.program = this.fb.group({
            name: [result.name, [Validators.required]],
            description: [result.description, [Validators.required]]
          })
        },
        () => {
          Swal.fire('Error', `Failed to load program with id: ${this.programid}`, 'error')
        }
      )
  }

  private getProgram(): Observable<Program>{
    this.programid = this.router.url.split('/')[3];
    return this.ps.getProgram(this.programid)
  }

  onSubmit(){
    this.ps.updateProgram(this.programid, new Program(
      this.program.value.name,
      this.program.value.description
    ))
  }
}


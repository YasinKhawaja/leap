import { Component, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from "@angular/forms";
import { Validators } from "@angular/forms";
import { Router } from "@angular/router";

@Component({
  selector: 'app-capability-add',
  templateUrl: './capability-add.component.html',
  styleUrls: ['./capability-add.component.css']
})
export class CapabilityAddComponent implements OnInit {

  capability = this.fb.group({
    name: ['', Validators.required]
  })

  constructor(private fb: FormBuilder,
              private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    console.log("Capability Added!");
    console.log(this.capability.value.name);
    this.router.navigate(['capability']);
  }

}

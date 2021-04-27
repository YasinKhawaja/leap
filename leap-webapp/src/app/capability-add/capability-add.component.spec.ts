import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { CapabilityAddComponent } from './capability-add.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

describe('CapabilityAddComponent', () => {
  let component: CapabilityAddComponent;
  let fixture: ComponentFixture<CapabilityAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[
        ReactiveFormsModule,
        FormsModule,
        RouterTestingModule
      ],
      declarations: [ CapabilityAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

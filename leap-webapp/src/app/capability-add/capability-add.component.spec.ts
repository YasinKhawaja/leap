import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { CapabilityAddComponent } from './capability-add.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

import { render } from "@testing-library/angular";
import { By } from '@angular/platform-browser';

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

  it('should be a valid form',() => {
    // fill in name in form
    component.capability.setValue({name: 'Test'});
    expect(component.capability.valid).toBeTruthy();
  })

  it('should add a capability', () => {
    // check if form is invalid
    expect(component.capability.invalid).toBeTruthy();
    let button = fixture.debugElement.query(By.css('button[type=submit]'));

    // check if button is disable
    expect(button.nativeElement.disabled).toBeTruthy();

    // fill in name in form
    component.capability.setValue({name: 'Test'});
    fixture.detectChanges();

    // check if button is enabled
    expect(button.nativeElement.disabled).toBeFalsy();

    // submit form
    button.nativeElement.click();
    fixture.detectChanges(); 
  })
});

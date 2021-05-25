import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { CapabilityAddComponent } from './capability-add.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

import { render } from "@testing-library/angular";
import { By } from '@angular/platform-browser';
import { HttpClient } from '@angular/common/http';
import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';

describe('CapabilityAddComponent', () => {
  let component: CapabilityAddComponent;
  let fixture: ComponentFixture<CapabilityAddComponent>;

  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[
        ReactiveFormsModule,
        FormsModule,
        RouterTestingModule,
        HttpClientTestingModule
      ],
      declarations: [ CapabilityAddComponent ]
    })
    .compileComponents();

    // Inject the http service and test controller for each test
    httpClient = TestBed.get(HttpClient);
    httpTestingController = TestBed.get(HttpTestingController);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  /*it('should be a valid form',() => {
    // fill in name in form
    component.capability.setValue({name: 'Test', paceOfChange: 'INNOVATIVE', tom: 'REPLICATION', 
    resourcesQuality: '4'});
    expect(component.capability.valid).toBeTruthy();
  })

  it('should add a capability', () => {
    // check if form is invalid
    expect(component.capability.invalid).toBeTruthy();
    let button = fixture.debugElement.query(By.css('button[type=submit]'));

    // check if button is disabled
    expect(button.nativeElement.disabled).toBeTruthy();

    // fill in name in form
    component.capability.setValue({name: 'Test', paceOfChange: 'INNOVATIVE', tom: 'REPLICATION', 
    resourcesQuality: '4'});
    fixture.detectChanges();

    // check if button is enabled
    expect(button.nativeElement.disabled).toBeFalsy();

    // submit form
    button.nativeElement.click();
    fixture.detectChanges(); 
  })*/
});

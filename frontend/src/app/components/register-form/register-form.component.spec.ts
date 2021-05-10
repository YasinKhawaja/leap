import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';

import { RegisterFormComponent } from './register-form.component';

describe('RegisterFormComponent', () => {
  let component: RegisterFormComponent;
  let fixture: ComponentFixture<RegisterFormComponent>;

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
      declarations: [ RegisterFormComponent ]
    })
    .compileComponents();

    // Inject the http service and test controller for each test
    httpClient = TestBed.get(HttpClient);
    httpTestingController = TestBed.get(HttpTestingController);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should be a valid form',() => {
    // fill in name in form
    component.company.setValue({vatNumber: 'Test', companyName: 'test', taxOffice: 'test', businessActivity: 'test', 
    email: 'test@gmail.com', streetName: 'dorp', houseNumber: '8',city:'Antwerpen',country:'Antwerpen',postcode:'2960'});
    expect(component.company.valid).toBeTruthy();
  })

  it('should add a user', () => {
    // check if form is invalid
    expect(component.company.invalid).toBeTruthy();
    let button = fixture.debugElement.query(By.css('button[type=submit]'));

    // check if button is disabled
    expect(button.nativeElement.disabled).toBeTruthy();

    // fill in name in form
    component.company.setValue({vatNumber: 'Test', companyName: 'test', taxOffice: 'test', businessActivity: 'test', 
    email: 'test@gmail.com', streetName: 'dorp', houseNumber: '8',city:'Antwerpen',country:'Antwerpen',postcode:'2960'});
    fixture.detectChanges();

    // check if button is enabled
    expect(button.nativeElement.disabled).toBeFalsy();

    // submit form
    button.nativeElement.click();
    fixture.detectChanges(); 
  })
});

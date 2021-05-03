import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityEditComponent } from './capability-edit.component';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';


describe('CapabilityEditComponent', () => {
  let component: CapabilityEditComponent;
  let fixture: ComponentFixture<CapabilityEditComponent>;

  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;


  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityEditComponent ],
      imports: [ HttpClientTestingModule,
        ReactiveFormsModule,
        FormsModule,
        RouterTestingModule]
    })
    .compileComponents();

    // Inject the http service and test controller for each test
    httpClient = TestBed.get(HttpClient);
    httpTestingController = TestBed.get(HttpTestingController);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

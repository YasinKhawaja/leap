import { HttpClient } from '@angular/common/http';
import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';

import { CapabilityDeleteComponent } from './capability-delete.component';

describe('CapabilityDeleteComponent', () => {
  let component: CapabilityDeleteComponent;
  let fixture: ComponentFixture<CapabilityDeleteComponent>;

  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityDeleteComponent ],
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
    fixture = TestBed.createComponent(CapabilityDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

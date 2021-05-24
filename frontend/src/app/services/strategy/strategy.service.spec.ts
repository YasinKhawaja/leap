import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';

import { StrategyService } from './strategy.service';

describe('StrategyService', () => {
  let service: StrategyService;

  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule,
        ReactiveFormsModule,
        FormsModule,
        RouterTestingModule]
    });
    service = TestBed.inject(StrategyService);

     // Inject the http service and test controller for each test
     httpClient = TestBed.get(HttpClient);
     httpTestingController = TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

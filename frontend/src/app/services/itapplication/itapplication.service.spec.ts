import { TestBed } from '@angular/core/testing';

import { ItapplicationService } from './itapplication.service';

describe('ItapplicationService', () => {
  let service: ItapplicationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ItapplicationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

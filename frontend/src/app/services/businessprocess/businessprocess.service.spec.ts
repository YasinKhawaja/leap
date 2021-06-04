import { TestBed } from '@angular/core/testing';

import { BusinessprocessService } from './businessprocess.service';

describe('BusinessprocessService', () => {
  let service: BusinessprocessService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BusinessprocessService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

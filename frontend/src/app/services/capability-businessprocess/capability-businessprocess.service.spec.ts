import { TestBed } from '@angular/core/testing';

import { CapabilityBusinessprocessService } from './capability-businessprocess.service';

describe('CapabilityBusinessprocessService', () => {
  let service: CapabilityBusinessprocessService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CapabilityBusinessprocessService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

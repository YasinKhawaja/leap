import { TestBed } from '@angular/core/testing';

import { CapabilityInformationService } from './capability-information.service';

describe('CapabilityInformationService', () => {
  let service: CapabilityInformationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CapabilityInformationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

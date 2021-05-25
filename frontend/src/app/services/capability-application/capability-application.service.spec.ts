import { TestBed } from '@angular/core/testing';

import { CapabilityApplicationService } from './capability-application.service';

describe('CapabilityApplicationService', () => {
  let service: CapabilityApplicationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CapabilityApplicationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

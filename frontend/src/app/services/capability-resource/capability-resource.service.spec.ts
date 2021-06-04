import { TestBed } from '@angular/core/testing';

import { CapabilityResourceService } from './capability-resource.service';

describe('CapabilityResourceService', () => {
  let service: CapabilityResourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CapabilityResourceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

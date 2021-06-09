import { TestBed } from '@angular/core/testing';

import { CapabilityProjectService } from './capability-project.service';

describe('CapabilityProjectService', () => {
  let service: CapabilityProjectService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CapabilityProjectService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

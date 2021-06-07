import { TestBed } from '@angular/core/testing';

import { CapabilityStrategyitemService } from './capability-strategyitem.service';

describe('CapabilityStrategyitemService', () => {
  let service: CapabilityStrategyitemService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CapabilityStrategyitemService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

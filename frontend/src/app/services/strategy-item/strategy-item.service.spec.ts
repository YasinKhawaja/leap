import { TestBed } from '@angular/core/testing';

import { StrategyItemService } from './strategy-item.service';

describe('StrategyItemService', () => {
  let service: StrategyItemService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StrategyItemService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

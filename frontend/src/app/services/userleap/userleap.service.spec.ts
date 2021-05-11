import { TestBed } from '@angular/core/testing';

import { UserleapService } from './userleap.service';

describe('UserleapService', () => {
  let service: UserleapService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserleapService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

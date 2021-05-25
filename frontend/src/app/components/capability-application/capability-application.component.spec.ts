import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityApplicationComponent } from './capability-application.component';

describe('CapabilityApplicationComponent', () => {
  let component: CapabilityApplicationComponent;
  let fixture: ComponentFixture<CapabilityApplicationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityApplicationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityApplicationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

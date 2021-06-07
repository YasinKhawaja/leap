import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityStrategyitemsComponent } from './capability-strategyitems.component';

describe('CapabilityStrategyitemsComponent', () => {
  let component: CapabilityStrategyitemsComponent;
  let fixture: ComponentFixture<CapabilityStrategyitemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityStrategyitemsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityStrategyitemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

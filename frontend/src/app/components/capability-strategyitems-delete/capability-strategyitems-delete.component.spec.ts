import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityStrategyitemsDeleteComponent } from './capability-strategyitems-delete.component';

describe('CapabilityStrategyitemsDeleteComponent', () => {
  let component: CapabilityStrategyitemsDeleteComponent;
  let fixture: ComponentFixture<CapabilityStrategyitemsDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityStrategyitemsDeleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityStrategyitemsDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

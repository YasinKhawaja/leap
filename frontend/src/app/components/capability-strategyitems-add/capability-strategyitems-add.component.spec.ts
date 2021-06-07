import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityStrategyitemsAddComponent } from './capability-strategyitems-add.component';

describe('CapabilityStrategyitemsAddComponent', () => {
  let component: CapabilityStrategyitemsAddComponent;
  let fixture: ComponentFixture<CapabilityStrategyitemsAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityStrategyitemsAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityStrategyitemsAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

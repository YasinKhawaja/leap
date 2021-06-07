import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityStrategyitemsEditComponent } from './capability-strategyitems-edit.component';

describe('CapabilityStrategyitemsEditComponent', () => {
  let component: CapabilityStrategyitemsEditComponent;
  let fixture: ComponentFixture<CapabilityStrategyitemsEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityStrategyitemsEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityStrategyitemsEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

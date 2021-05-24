import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StrategyItemsComponent } from './strategy-items.component';

describe('StrategyItemsComponent', () => {
  let component: StrategyItemsComponent;
  let fixture: ComponentFixture<StrategyItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StrategyItemsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StrategyItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

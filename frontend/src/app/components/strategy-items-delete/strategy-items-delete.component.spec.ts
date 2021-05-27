import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StrategyItemsDeleteComponent } from './strategy-items-delete.component';

describe('StrategyItemsDeleteComponent', () => {
  let component: StrategyItemsDeleteComponent;
  let fixture: ComponentFixture<StrategyItemsDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StrategyItemsDeleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StrategyItemsDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

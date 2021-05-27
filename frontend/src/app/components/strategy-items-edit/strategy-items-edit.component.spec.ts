import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StrategyItemsEditComponent } from './strategy-items-edit.component';

describe('StrategyItemsEditComponent', () => {
  let component: StrategyItemsEditComponent;
  let fixture: ComponentFixture<StrategyItemsEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StrategyItemsEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StrategyItemsEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

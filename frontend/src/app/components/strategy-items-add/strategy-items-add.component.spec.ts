import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StrategyItemsAddComponent } from './strategy-items-add.component';

describe('StrategyItemsAddComponent', () => {
  let component: StrategyItemsAddComponent;
  let fixture: ComponentFixture<StrategyItemsAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StrategyItemsAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StrategyItemsAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

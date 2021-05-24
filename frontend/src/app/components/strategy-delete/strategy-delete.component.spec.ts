import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StrategyDeleteComponent } from './strategy-delete.component';

describe('StrategyDeleteComponent', () => {
  let component: StrategyDeleteComponent;
  let fixture: ComponentFixture<StrategyDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StrategyDeleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StrategyDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

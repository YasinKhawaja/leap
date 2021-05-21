import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItapplicationComponent } from './itapplication.component';

describe('ItapplicationComponent', () => {
  let component: ItapplicationComponent;
  let fixture: ComponentFixture<ItapplicationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItapplicationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ItapplicationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItapplicationAddComponent } from './itapplication-add.component';

describe('ItapplicationAddComponent', () => {
  let component: ItapplicationAddComponent;
  let fixture: ComponentFixture<ItapplicationAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItapplicationAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ItapplicationAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

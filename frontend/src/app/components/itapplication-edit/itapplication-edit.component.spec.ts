import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItapplicationEditComponent } from './itapplication-edit.component';

describe('ItapplicationEditComponent', () => {
  let component: ItapplicationEditComponent;
  let fixture: ComponentFixture<ItapplicationEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItapplicationEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ItapplicationEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

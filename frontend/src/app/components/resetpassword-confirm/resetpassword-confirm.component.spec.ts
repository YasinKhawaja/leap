import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResetpasswordConfirmComponent } from './resetpassword-confirm.component';

describe('ResetpasswordConfirmComponent', () => {
  let component: ResetpasswordConfirmComponent;
  let fixture: ComponentFixture<ResetpasswordConfirmComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResetpasswordConfirmComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResetpasswordConfirmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

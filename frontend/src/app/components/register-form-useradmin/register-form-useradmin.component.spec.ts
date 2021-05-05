import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterFormUseradminComponent } from './register-form-useradmin.component';

describe('RegisterFormUseradminComponent', () => {
  let component: RegisterFormUseradminComponent;
  let fixture: ComponentFixture<RegisterFormUseradminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterFormUseradminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterFormUseradminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

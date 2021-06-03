import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessprocessEditComponent } from './businessprocess-edit.component';

describe('BusinessprocessEditComponent', () => {
  let component: BusinessprocessEditComponent;
  let fixture: ComponentFixture<BusinessprocessEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BusinessprocessEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BusinessprocessEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

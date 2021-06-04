import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessprocessDeleteComponent } from './businessprocess-delete.component';

describe('BusinessprocessDeleteComponent', () => {
  let component: BusinessprocessDeleteComponent;
  let fixture: ComponentFixture<BusinessprocessDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BusinessprocessDeleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BusinessprocessDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

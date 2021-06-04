import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessprocessAddComponent } from './businessprocess-add.component';

describe('BusinessprocessAddComponent', () => {
  let component: BusinessprocessAddComponent;
  let fixture: ComponentFixture<BusinessprocessAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BusinessprocessAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BusinessprocessAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

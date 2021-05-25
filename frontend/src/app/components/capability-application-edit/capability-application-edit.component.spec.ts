import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityApplicationEditComponent } from './capability-application-edit.component';

describe('CapabilityApplicationEditComponent', () => {
  let component: CapabilityApplicationEditComponent;
  let fixture: ComponentFixture<CapabilityApplicationEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityApplicationEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityApplicationEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

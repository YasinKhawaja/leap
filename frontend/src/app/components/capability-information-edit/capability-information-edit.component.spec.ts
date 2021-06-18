import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityInformationEditComponent } from './capability-information-edit.component';

describe('CapabilityInformationEditComponent', () => {
  let component: CapabilityInformationEditComponent;
  let fixture: ComponentFixture<CapabilityInformationEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityInformationEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityInformationEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

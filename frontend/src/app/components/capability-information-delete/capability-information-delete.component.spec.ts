import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityInformationDeleteComponent } from './capability-information-delete.component';

describe('CapabilityInformationDeleteComponent', () => {
  let component: CapabilityInformationDeleteComponent;
  let fixture: ComponentFixture<CapabilityInformationDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityInformationDeleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityInformationDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

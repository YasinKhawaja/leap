import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityInformationComponent } from './capability-information.component';

describe('CapabilityInformationComponent', () => {
  let component: CapabilityInformationComponent;
  let fixture: ComponentFixture<CapabilityInformationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityInformationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

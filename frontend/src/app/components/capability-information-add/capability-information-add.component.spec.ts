import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityInformationAddComponent } from './capability-information-add.component';

describe('CapabilityInformationAddComponent', () => {
  let component: CapabilityInformationAddComponent;
  let fixture: ComponentFixture<CapabilityInformationAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityInformationAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityInformationAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

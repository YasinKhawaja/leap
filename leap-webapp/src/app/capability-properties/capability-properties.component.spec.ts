import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityPropertiesComponent } from './capability-properties.component';

describe('CapabilityPropertiesComponent', () => {
  let component: CapabilityPropertiesComponent;
  let fixture: ComponentFixture<CapabilityPropertiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityPropertiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityPropertiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

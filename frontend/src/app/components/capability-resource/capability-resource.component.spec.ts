import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityResourceComponent } from './capability-resource.component';

describe('CapabilityResourceComponent', () => {
  let component: CapabilityResourceComponent;
  let fixture: ComponentFixture<CapabilityResourceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityResourceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityResourceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

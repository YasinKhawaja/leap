import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityResourceDeleteComponent } from './capability-resource-delete.component';

describe('CapabilityResourceDeleteComponent', () => {
  let component: CapabilityResourceDeleteComponent;
  let fixture: ComponentFixture<CapabilityResourceDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityResourceDeleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityResourceDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityResourceAddComponent } from './capability-resource-add.component';

describe('CapabilityResourceAddComponent', () => {
  let component: CapabilityResourceAddComponent;
  let fixture: ComponentFixture<CapabilityResourceAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityResourceAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityResourceAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

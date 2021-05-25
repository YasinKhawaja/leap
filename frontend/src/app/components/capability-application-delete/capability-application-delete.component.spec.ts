import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityApplicationDeleteComponent } from './capability-application-delete.component';

describe('CapabilityApplicationDeleteComponent', () => {
  let component: CapabilityApplicationDeleteComponent;
  let fixture: ComponentFixture<CapabilityApplicationDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityApplicationDeleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityApplicationDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

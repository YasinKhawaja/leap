import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityDeleteComponent } from './capability-delete.component';

describe('CapabilityDeleteComponent', () => {
  let component: CapabilityDeleteComponent;
  let fixture: ComponentFixture<CapabilityDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityDeleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

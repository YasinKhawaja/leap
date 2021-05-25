import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityApplicationAddComponent } from './capability-application-add.component';

describe('CapabilityApplicationAddComponent', () => {
  let component: CapabilityApplicationAddComponent;
  let fixture: ComponentFixture<CapabilityApplicationAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityApplicationAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityApplicationAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

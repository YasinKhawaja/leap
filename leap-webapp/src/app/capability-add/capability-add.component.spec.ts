import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityAddComponent } from './capability-add.component';

describe('CapabilityAddComponent', () => {
  let component: CapabilityAddComponent;
  let fixture: ComponentFixture<CapabilityAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

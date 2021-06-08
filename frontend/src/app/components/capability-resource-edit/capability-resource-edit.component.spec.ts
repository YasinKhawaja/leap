import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityResourceEditComponent } from './capability-resource-edit.component';

describe('CapabilityResourceEditComponent', () => {
  let component: CapabilityResourceEditComponent;
  let fixture: ComponentFixture<CapabilityResourceEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityResourceEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityResourceEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

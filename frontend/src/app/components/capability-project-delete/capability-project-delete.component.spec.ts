import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityProjectDeleteComponent } from './capability-project-delete.component';

describe('CapabilityProjectDeleteComponent', () => {
  let component: CapabilityProjectDeleteComponent;
  let fixture: ComponentFixture<CapabilityProjectDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityProjectDeleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityProjectDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

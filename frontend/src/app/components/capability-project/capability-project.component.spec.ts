import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityProjectComponent } from './capability-project.component';

describe('CapabilityProjectComponent', () => {
  let component: CapabilityProjectComponent;
  let fixture: ComponentFixture<CapabilityProjectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityProjectComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityProjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

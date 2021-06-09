import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityProjectAddComponent } from './capability-project-add.component';

describe('CapabilityProjectAddComponent', () => {
  let component: CapabilityProjectAddComponent;
  let fixture: ComponentFixture<CapabilityProjectAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityProjectAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityProjectAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

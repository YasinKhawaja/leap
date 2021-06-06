import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityBusinessprocessComponent } from './capability-businessprocess.component';

describe('CapabilityBusinessprocessComponent', () => {
  let component: CapabilityBusinessprocessComponent;
  let fixture: ComponentFixture<CapabilityBusinessprocessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityBusinessprocessComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityBusinessprocessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

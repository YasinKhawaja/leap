import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityBusinessprocessDeleteComponent } from './capability-businessprocess-delete.component';

describe('CapabilityBusinessprocessDeleteComponent', () => {
  let component: CapabilityBusinessprocessDeleteComponent;
  let fixture: ComponentFixture<CapabilityBusinessprocessDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityBusinessprocessDeleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityBusinessprocessDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

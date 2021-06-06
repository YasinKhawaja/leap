import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilityBusinessprocessAddComponent } from './capability-businessprocess-add.component';

describe('CapabilityBusinessprocessAddComponent', () => {
  let component: CapabilityBusinessprocessAddComponent;
  let fixture: ComponentFixture<CapabilityBusinessprocessAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapabilityBusinessprocessAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilityBusinessprocessAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubcapabilityAddComponent } from './subcapability-add.component';

describe('SubcapabilityAddComponent', () => {
  let component: SubcapabilityAddComponent;
  let fixture: ComponentFixture<SubcapabilityAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubcapabilityAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubcapabilityAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

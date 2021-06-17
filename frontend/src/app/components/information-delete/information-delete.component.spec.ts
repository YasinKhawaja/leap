import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InformationDeleteComponent } from './information-delete.component';

describe('InformationDeleteComponent', () => {
  let component: InformationDeleteComponent;
  let fixture: ComponentFixture<InformationDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InformationDeleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InformationDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

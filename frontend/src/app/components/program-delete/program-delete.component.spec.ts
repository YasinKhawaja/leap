import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgramDeleteComponent } from './program-delete.component';

describe('ProgramDeleteComponent', () => {
  let component: ProgramDeleteComponent;
  let fixture: ComponentFixture<ProgramDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProgramDeleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProgramDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

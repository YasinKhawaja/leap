import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItapplicationDeleteComponent } from './itapplication-delete.component';

describe('ItapplicationDeleteComponent', () => {
  let component: ItapplicationDeleteComponent;
  let fixture: ComponentFixture<ItapplicationDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItapplicationDeleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ItapplicationDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import * as Rx from 'rxjs';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import { EnvironmentComponent } from './environment.component';


describe('EnvironmentComponent', () => {
  let component: EnvironmentComponent;
  let fixture: ComponentFixture<EnvironmentComponent>;
  let service: EnvironmentService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientTestingModule
      ],
      declarations: [
        EnvironmentComponent
      ],
      providers: [
        EnvironmentService
      ]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnvironmentComponent);
    component = fixture.componentInstance;
    service = fixture.debugElement.injector.get(EnvironmentService);
    fixture.detectChanges();
  });

  // Default test
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call ngOnInit', () => {
    // Given
    var spy_getAllEnvironments = spyOn(service, "getAllEnvironments").and.callFake(() => {
      return Rx.of([
        { id: 1, name: 'A', capabilities: [] },
        { id: 2, name: 'B', capabilities: [] }
      ]);
    });

    // When
    component.ngOnInit();

    // Then
    expect(component.environments).toBeDefined();
    expect(component.environments).toBeInstanceOf(Array);
    expect(component.environments).toHaveSize(2);
  });
});

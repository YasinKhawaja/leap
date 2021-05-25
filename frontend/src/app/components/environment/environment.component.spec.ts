import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import * as Rx from 'rxjs';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import { EnvironmentComponent } from './environment.component';


describe('EnvironmentComponent', () => {
  let component: EnvironmentComponent;
  let fixture: ComponentFixture<EnvironmentComponent>;
  let eService: EnvironmentService;

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
    eService = fixture.debugElement.injector.get(EnvironmentService);
    fixture.detectChanges();
  });

  // Default test
  it('should create component', () => {
    expect(component).toBeTruthy();
  });

  // Positive test "getAllEnvironments" method
  it('should call & save 3 envs in "environments" prop', () => {
    // Given
    var spyService_getAllEnvironments = spyOn(eService, "getAllEnvironments").and.callFake(() => {
      return Rx.of([
        { id: '1', name: 'EnvA', capabilities: [] },
        { id: '2', name: 'EnvB', capabilities: [] },
        { id: '3', name: 'EnvC', capabilities: [] }
      ])
    });

    // When
    component.getAllEnvironments();

    // Then
    expect(spyService_getAllEnvironments).toHaveBeenCalled();
    expect(component.environments).toHaveSize(3);
  });

  // Negative test "getAllEnvironments" method
  it('should throw error & save none', () => {
    // Given
    var spy_getAllEnvironments = spyOn(component, "getAllEnvironments").and.throwError('ERROR');

    // Then
    expect(spy_getAllEnvironments).toThrowError('ERROR');
    expect(component.environments).toHaveSize(0);
  });
});

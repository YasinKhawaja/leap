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

  // Test "getAllEnvironments" method
  it('should get all environments', () => {
    // Given
    spyOn(eService, "getAllEnvironments").and.callFake(() => {
      return Rx.of([
        // Expected envs to be returned
        { id: '1', name: 'EnvA', capabilities: [] },
        { id: '2', name: 'EnvB', capabilities: [] },
        { id: '3', name: 'EnvC', capabilities: [] }
      ])
    });

    // When
    // Actual envs returned and saved in "environments" prop
    component.getAllEnvironments();

    // Then
    expect(component.environments[0].name).toBe('EnvA');
    expect(component.environments[1].name).toBe('EnvB');
    expect(component.environments[2].name).toBe('EnvC');
  });
});

import { of } from 'rxjs';
import { Environment } from 'src/app/classes/environment/environment';
import { EnvironmentService } from './environment.service';


describe('EnvironmentService', () => {
  let httpClientSpy: { get: jasmine.Spy };
  let environmentService: EnvironmentService;

  beforeEach(() => {
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get', 'post', 'put', 'delete']);
    environmentService = new EnvironmentService(httpClientSpy as any);
  });

  // Test "getAllEnvironments" API call
  it('should get all environments', (done: DoneFn) => {
    // Given
    var expEnvs: Environment[] = [
      { id: '1', name: 'EnvA', capabilities: [] },
      { id: '2', name: 'EnvB', capabilities: [] },
      { id: '3', name: 'EnvC', capabilities: [] }
    ];

    httpClientSpy.get.and.returnValue(of(expEnvs));

    // When
    environmentService.getAllEnvironments()
      .subscribe(
        envs => {
          // Then
          expect(envs).toEqual(expEnvs);
          done();
        },
        done.fail
      );
  });
});

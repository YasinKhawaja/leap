import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { Environment } from 'src/app/classes/environment/environment';
import { EnvironmentService } from './environment.service';


describe('EnvironmentService', () => {
  let service: EnvironmentService;
  // HTTP
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });

    service = TestBed.inject(EnvironmentService);
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  // GET all (2) environments
  it('should GET 2 environments', () => {
    var url = 'http://localhost:8080/api/environments';

    httpClient.get<Environment>(url).subscribe(
      res => expect(res).toBeDefined()
    );
  });
});

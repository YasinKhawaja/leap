import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StrategyItem } from 'src/app/classes/strategy-item/strategyItem';

@Injectable({
  providedIn: 'root'
})
export class StrategyItemService {

  private strategyItemServiceURI: string = 'http://localhost:8080/api/envs';
  private contentHeaders: HttpHeaders;

  

  constructor(private http: HttpClient) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
 }


}

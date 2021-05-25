import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StrategyItem } from 'src/app/classes/strategy-item/strategyItem';

@Injectable({
  providedIn: 'root'
})
export class StrategyItemService {

  private strategyItemServiceURI: string = 'http://localhost:8080/api/strsItems';
  private contentHeaders: HttpHeaders;

  

  constructor(private http: HttpClient) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
 }


 public getAllStrategyItemInStrategy(strId: string): Observable<StrategyItem[]> {
  var url = `${this.strategyItemServiceURI}/${strId}`;

  return this.http.get<StrategyItem[]>(url);
}

}

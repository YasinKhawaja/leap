import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StrategyItem } from 'src/app/classes/strategy-item/strategyItem';

@Injectable({
  providedIn: 'root'
})
export class StrategyItemService {

  private strategyItemServiceURI: string = 'http://localhost:8080/api/strategyItems';
  private contentHeaders: HttpHeaders;

  

  constructor(private http: HttpClient) {
    this.contentHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
 }

 getAllStrategies(): Observable<StrategyItem[]> {
  let url = `${this.strategyItemServiceURI}`;
  return this.http.get<StrategyItem[]>(url);
}

  getAllStrategyItemInStrategy(strId: string): Observable<StrategyItem[]> {
  var url = `${this.strategyItemServiceURI}`;

  return this.http.get<StrategyItem[]>(url, { params: { strId: strId } });
}

getAllStrategyItemsInStrategyByName(strategyName: string): Observable<StrategyItem[]> {
  var url = `${this.strategyItemServiceURI}/strategy`;

  return this.http.get<StrategyItem[]>(url, { params: { strategyName: strategyName } });
}

 // To GET 
 getStrategyItem(strId: string, strItemId: string): Observable<StrategyItem> {
  var url = `${this.strategyItemServiceURI}/${strItemId}`;

  return this.http.get<StrategyItem>(url, { params: { strId: strId } });
}

// To CREATE 
createStrategyItem(strId: string, strItem: StrategyItem): Observable<StrategyItem> {
  var url = `${this.strategyItemServiceURI}`;

  return this.http.post<StrategyItem>(url, strItem, { params: { strId: strId}});
}

 // To UPDATE => werkt
 updateStrategyItem(strId: string, strItemId: string, strItem: StrategyItem): Observable<StrategyItem> {
  var url = `${this.strategyItemServiceURI}/${strItemId}`;

  return this.http.put<StrategyItem>(url, strItem, { params: { strId: strId } });
}

// To DELETE
deleteStrategyItem(strId: string, strItemId: string): Observable<{}> {
  var url = `${this.strategyItemServiceURI}/${strItemId}`;

  return this.http.delete(url, { params: { strId: strId } });
}


}

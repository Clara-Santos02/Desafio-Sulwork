import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ItemCafe } from '../models/item.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ItemCafeService {
  private base = 'http://localhost:8081/cafe';

  constructor(private http: HttpClient) {}

  listar(): Observable<ItemCafe[]> {
    return this.http.get<ItemCafe[]>(this.base);
  }

  criar(item: ItemCafe) {
    return this.http.post(this.base, item);
  }
}
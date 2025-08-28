import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Colaborador } from '../models/colaborador.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ColaboradorService {
  private base = 'http://localhost:8081/colaboradores';

  constructor(private http: HttpClient) {}

  listar(): Observable<Colaborador[]> {
    return this.http.get<Colaborador[]>(this.base);
  }

  criar(col: Colaborador) {
    return this.http.post(this.base, col);
  }
}
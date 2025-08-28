import { Component, OnInit } from '@angular/core';
import { ItemCafeService } from '../../services/item-cafe.service';
import { ColaboradorService } from '../../services/colaborador.service';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
})
export class ListaComponent implements OnInit {
  itens: any[] = [];
  colaboradores: any[] = [];
  carregando = false;
  erro = '';

  constructor(
    private itemService: ItemCafeService,
    private colaboradorService: ColaboradorService
  ) {}

  ngOnInit() {
    this.loadAll();
  }

  loadAll() {
    this.carregando = true;
    this.itemService.listar().subscribe({
      next: (it) => {
        this.itens = it;
        this.carregando = false;
      },
      error: (e) => {
        this.erro = 'Erro ao carregar itens';
        this.carregando = false;
      },
    });

    this.colaboradorService.listar().subscribe({
      next: (cols) => (this.colaboradores = cols),
      error: () => {},
    });
  }
}

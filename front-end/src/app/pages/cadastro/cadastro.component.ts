import { Component, OnInit } from '@angular/core';
import { ColaboradorService } from '../../services/colaborador.service';
import { ItemCafeService } from '../../services/item-cafe.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Colaborador } from '../../models/colaborador.model';
import { ItemCafe } from '../../models/item.model';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html'
})
export class CadastroComponent implements OnInit {
  colaboradores: Colaborador[] = [];
  msg = '';

  colaboradorForm = this.fb.group({
    nome: ['', Validators.required],
    cpf: ['', [Validators.required, Validators.minLength(11), Validators.maxLength(11)]]
  });

  itemForm = this.fb.group({
    nomeItem: ['', Validators.required],
    data: ['', Validators.required], 
    colaboradorId: [null, Validators.required]
  });

  constructor(
    private fb: FormBuilder,
    private colaboradorService: ColaboradorService,
    private itemService: ItemCafeService
  ) {}

  ngOnInit() {
    this.loadColaboradores();
  }

  loadColaboradores() {
    this.colaboradorService.listar().subscribe({
      next: cols => this.colaboradores = cols,
      error: () => this.colaboradores = []
    });
  }

  salvarColaborador() {
    if (this.colaboradorForm.invalid) return;
    const dto = this.colaboradorForm.value;
    this.colaboradorService.criar(dto).subscribe({
      next: () => { this.msg = 'Colaborador cadastrado!'; this.colaboradorForm.reset(); this.loadColaboradores(); },
      error: err => this.msg = err?.error || 'Erro ao cadastrar'
    });
  }

  salvarItem() {
    if (this.itemForm.invalid) return;

    const dataIso = this.formatToIso(this.itemForm.value.data);
    const dto: ItemCafe = {
      nomeItem: this.itemForm.value.nomeItem,
      data: dataIso,
      status: 'PENDENTE',
      colaboradorId: +this.itemForm.value.colaboradorId
    };
    this.itemService.criar(dto).subscribe({
      next: () => { this.msg = 'Item cadastrado!'; this.itemForm.reset(); },
      error: err => this.msg = err?.error || 'Erro ao cadastrar item'
    });
  }

  private formatToIso(value: string) {
    return value;
  }
}
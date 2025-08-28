import { Colaborador } from "./colaborador.model";

export interface ItemCafe {
  id?: number;
  nomeItem: string;
  data: string; 
  status?: string;
  colaboradorId: number;
  colaborador?: Colaborador;
}
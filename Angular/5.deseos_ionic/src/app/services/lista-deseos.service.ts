import { Injectable } from '@angular/core';
import { Lista } from '../clases/listas';

@Injectable()
export class ListaDeseosServices {

  listas: Lista[] = [];

  constructor() {
    this.loadLocalStorage();
  }

  saveLocalStorage() {
    localStorage.setItem("dataListas", JSON.stringify(this.listas));
  }

  loadLocalStorage() {    
    if (localStorage.getItem("dataListas")) {
      let dataListas = localStorage.getItem("dataListas");
      this.listas = JSON.parse(dataListas);
    }    
  }

  agregarLista( lst : Lista) {    
    if (lst != null) {
      this.listas.push(lst);
      this.saveLocalStorage();
    }    
  }

  eliminarLista (idx: number) {
    this.listas.splice(idx,1);
    this.saveLocalStorage();
  }

}

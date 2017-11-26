import { Component, OnInit } from '@angular/core';
import { ListaDeseosServices } from '../../app/services/lista-deseos.service';
import {  NavController } from 'ionic-angular'; // Para navegar
import {  AgregarComponent } from "../agregar/agregar.component";
import {  DetalleComponent } from "../detalle/detalle.component";

@Component({
  selector: 'app-pendientes',
  templateUrl: 'pendientes.component.html',
})
export class PendientesComponent implements OnInit {

  constructor(private listaDeseosServices: ListaDeseosServices,
             private navController : NavController) {
    console.log("Constructor Pendientes");
  }

  ngOnInit() { }

  irAgregar() {
    this.navController.push(AgregarComponent);
  }

  verDetalle(lista , index) {
    this.navController.push(DetalleComponent , {
      lista,index
    }); // Pasar Parametros
  }
}

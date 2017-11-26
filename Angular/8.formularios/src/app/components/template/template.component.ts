import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-template',
  templateUrl: './template.component.html',
  styles: [`
    .ng-invalid.ng-touched:not(form) {
      border: 1px solid red;
    }
    `]
})
export class TemplateComponent implements OnInit {

  nombre: string;

  usuario = {
    nombre: null,
    apellido: null,
    correo: null,
    pais: "-1",
    sexo: null,
    acepta: false,
  };

  paises = [
    { codigo: "ALE", nombre: "Alemania" },
    { codigo: "COL", nombre: "Colombia" },
    { codigo: "FRA", nombre: "Francia" },
  ];

  sexos: any[] = ["Hombre", "Mujer", "Indeciso"];

  constructor() { }

  ngOnInit() {
  }


  guardar(forma: NgForm) {
    console.log("ngForm:", forma);
    console.log("Usuario:", this.usuario);
  }

}

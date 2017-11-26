import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  nombre: string = "cristian";

  activar: boolean = false;

  arreglo: number[] = [1, 2, 3, 4, 5, 6];

  PI = Math.PI;

  a = 0.234;

  salario = 4500.850;

  hoy: Date = new Date();

  objHeroe = {
    nombre: "Cristian",
    clave: "clave",
    edad: 500,
    direccion: {
      calle: "Primera",
      casa: "Calle XXX"
    }
  }

  vlrPromesa = new Promise((resolve, reject) =>
    setTimeout(() => resolve('Llego la data!'), 3500));

  video: string = "Up4uouRKg6I";

  changeActivacion() {
    this.activar = !this.activar;
  }

}

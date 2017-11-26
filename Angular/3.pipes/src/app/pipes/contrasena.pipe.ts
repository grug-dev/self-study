import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'contrasena'
})
export class ContrasenaPipe implements PipeTransform {

  transform(value: string, activar: boolean): string {
    let contrasena: string = value;
    if (activar) {
      let re = /[a-z]/gi;
      contrasena = value.replace(re, "\*");
    }
    return contrasena;
  }

}

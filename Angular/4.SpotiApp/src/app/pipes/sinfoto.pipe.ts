import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'sinfoto'
})
export class SinfotoPipe implements PipeTransform {

  transform(value: any[]): string {
    let sinFoto = "assets/img/noimage.png";
    if (!value) {
      return "assets/img/noimage.png";
    }

    return (value.length > 0 ? value[1].url : sinFoto);
  }

}

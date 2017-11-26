import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'keys',
  pure: false // Este pendiente si el array se cambia
})
export class KeysPipe implements PipeTransform {

  transform(value: any): any {

    let keys = [];

    for (let key in value) {
      console.log("KEY PIPE => ", key);
      keys.push(key);
    }

    return keys;

  }

}

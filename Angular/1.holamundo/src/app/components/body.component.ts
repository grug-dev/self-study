// En un component siempre se dbee importar
import {Component} from '@angular/core';

@Component({
  selector: 'app-body',
  templateUrl: 'body.component.html'
})

export class BodyComponent {

  mostrar:boolean = true;

  frase:any = {
    mensaje: "Un gran poder" ,
    autor: "Cristian "
  };

  personajes:string[] = ["Spiderman", "Venom" , "Superman"];

}

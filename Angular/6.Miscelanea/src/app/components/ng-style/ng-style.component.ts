import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ng-style',
  template: `
    <code>[ngStyle]=" 'font-size': tamano + 'px' , 'color': 'red'"</code>
    <p [ngStyle]=" { 'font-size': tamano + 'px' , 'color': 'red'}">
        Primera forma de usar ngStyle         
    </p>

    <code>[style.fontSize] = tamano + px</code>
    <p [style.fontSize]="tamano + 'px'">
        ngStyle con Objetos. No debe usarse guion, sino camelCase. 
    </p>

    <button type="button" name="" id="" class="btn btn-primary" (click) = "tamano = tamano + 2" >
      <i class="fa fa-plus" aria-hidden="true"></i>
    </button>

   <button type="button" name="" id="" class="btn btn-primary" (click) = "tamano = tamano - 2" >
       <i class="fa fa-minus" aria-hidden="true"></i>
   </button>

  `,
  styles: []
})
export class NgStyleComponent implements OnInit {

  tamano:number = 10;

  constructor() { }

  ngOnInit() {
  }

}

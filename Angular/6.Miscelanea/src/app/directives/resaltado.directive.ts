import { Directive , ElementRef , HostListener , Input} from '@angular/core';

@Directive({
  selector: '[appResaltado]'
})
export class ResaltadoDirective {

  constructor(private elemento:ElementRef) {
      
  }

  @Input("appResaltado") nuevoColor:string;

   // Para manejar evento de entrada del mouse a la directiva.
   @HostListener('mouseenter') mouseEntro() {
    this.resaltar(this.nuevoColor);
   }

  @HostListener('mouseleave') mouseSalio() {
    this.resaltar();
  }

  private resaltar ( color : string = null) {
    this.elemento.nativeElement.style.backgroundColor = color;
  }
  

}

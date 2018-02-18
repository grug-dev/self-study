import { Component, OnInit } from '@angular/core';
import { FileItem } from '../../models/file-item';
import { CargaImagesService } from '../../services/carga-images.service';

@Component({
  selector: 'app-carga',
  templateUrl: './carga.component.html',
  styles: []
})
export class CargaComponent implements OnInit {

  archivos: FileItem[] = [];
  isOverElement = false;

  constructor(public srvCargarImg: CargaImagesService) {

   }

  ngOnInit() {
  }

  cargarImagenes() {
    this.srvCargarImg.cargarImagenesFireBase( this.archivos);
  }

  subirArchivo() {
    this.srvCargarImg.cargarArchivo( this.archivos[0]);
  }

  limpiar(){
    this.archivos = [];
  }

}

import { Injectable } from '@angular/core';
import { AngularFirestore } from 'angularfire2/firestore';
import * as firebase from 'firebase';
import { FileItem } from '../models/file-item';
import { HttpClient, HttpHeaders  } from '@angular/common/http';
import 'rxjs/Rx';

@Injectable()
export class CargaImagesService {

  private carpetaImg = 'img';

  constructor(private db: AngularFirestore, private http: HttpClient) {

  }

  private guardarImagen( imagen: { nombre: string , url: string}) {
    this.db.collection(`/${this.carpetaImg}`)
    .add(imagen);
  }

  public cargarImagenesFireBase( imagenes: FileItem[] ) {
    console.log(imagenes);
  }

  public cargarArchivo (archivo: FileItem) {
    let fd = new FormData();
    fd.append('file', archivo.archivo);
    
    const url = 'http://localhost:9999/cube/upload';
     const headers = new HttpHeaders({      
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods': 'GET, POST, OPTIONS, PUT, PATCH, DELETE',
      'Access-Control-Allow-Headers': 'X-Requested-With,content-type',
      'Access-Control-Allow-Credentials': true 
    });
      
    this.http.post(url, fd, { headers } )
    .map (  (resp: any ) => {
      console.log(resp);
      // Operaciones necesarias...
      	  return resp;
    })
    .subscribe( map => {
      console.log(map);
    });
  }

}

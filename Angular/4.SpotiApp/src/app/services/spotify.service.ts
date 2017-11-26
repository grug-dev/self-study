import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { HttpModule } from '@angular/http';

import 'rxjs/add/operator/map';

@Injectable()
export class SpotifyService {

  artistas: any[] = [];

  urlBusqueda: string = "https://api.spotify.com/v1/search/";
  urlArtista: string = "https://api.spotify.com/v1/artists/";
  token: string = " BQBpxGcrmYa_L0BgNruo2Eccr_PY-wltXCbWxFjPIJsSmtJ3s4km5ZJff-gRdLBlyAqH353sbM30JoEXuCYB0A";

  constructor(private http: Http) {

  }


  imprimir() {
    console.log("Imprimiendo");
  }


  getArtista(id: string) {
    let query = `${id}`;
    let url = this.urlArtista + query;
    let headers = new Headers();
    headers.append('authorization', 'Bearer ' + this.token);

    return this.http.get(url, { headers })
      .map(respuesta => {
        console.log(respuesta.json());
        return respuesta.json();
      })
  }

  getArtistas(termino: string) {
    let query = `?q=${termino}&type=artist`;
    let url = this.urlBusqueda + query;

    let headers = new Headers();
    headers.append('authorization', 'Bearer ' + this.token);

    // Siga trabajando, pero estè pendiente la respuesta.
    return this.http.get(url, { headers }) // Va a regresar un observable. Y podemos convertir la respuseta en un objeto con el .map
      .map(res => {
        //console.log(res.json().artists.items);
        this.artistas = res.json().artists.items;

        console.log(res.json().artists.items);
        return res.json().artists.itemss;

      })
      ;
  }

  getTopTracks(id: string) {
    let query = `${id}/top-tracks?country=US`;
    let url = this.urlArtista + query;
    let headers = new Headers();
    headers.append('authorization', 'Bearer ' + this.token);

    return this.http.get(url, { headers })
      .map(respuesta => {
        console.log(respuesta.json().tracks);
        return respuesta.json().tracks;
      })
  }

}

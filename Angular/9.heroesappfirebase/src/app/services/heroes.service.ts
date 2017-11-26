import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Heroe } from '../interfaces/heroe.interface';
import 'rxjs/Rx';

@Injectable()
export class HeroesService {

  fireBaseURL = "https://heroesapp-84a85.firebaseio.com";

  constructor(private http: Http) { }


  /**
  POST
  */
  insertHeroe(heroe: Heroe) {
    let cuerpo = JSON.stringify(heroe);
    let customHeaders = new Headers({
      'Content-Type': 'application/json'
    });
    // post = (url , body , headers)
    // El mapa toco importarlo de: import 'rxjs/Rx';
    return this.http.post(this.fireBaseURL + "/heroes.json", cuerpo, { headers: customHeaders }).map(res => {
      console.log(res.json());
      return res.json();
    });
  }

  updateHeroe(heroe: Heroe, key: string) {
    let cuerpo = JSON.stringify(heroe);
    let customHeaders = new Headers({
      'Content-Type': 'application/json'
    });

    let url = `${this.fireBaseURL}/heroes/${key}.json `;

    console.log("URL => " + url);

    return this.http.put(url, cuerpo, { headers: customHeaders }).map(res => {
      return res.json();
    });
  }

  getHeroe(key: string) {

    let customHeaders = new Headers({
      'Content-Type': 'application/json'
    });

    let url = `${this.fireBaseURL}/heroes/${key}.json `;

    console.log("URL => " + url);

    return this.http.get(url, { headers: customHeaders }).map(res => {
      return res.json();
    });
  }

  getAllHeroes() {
    let url = `${this.fireBaseURL}/heroes.json`;
    let customHeaders = new Headers({
      'Content-Type': 'application/json'
    });
    return this.http.get(url, { headers: customHeaders }).map(res => {
      return res.json();
    })

  }

  deleteHeroe(key: string) {
    let url = `${this.fireBaseURL}/heroes/${key}.json`;
    let customHeaders = new Headers({
      'Content-Type': 'application/json'
    });

    return this.http.delete(url, { headers: customHeaders }).map(res => {
      return res.json();
    });

  }

}

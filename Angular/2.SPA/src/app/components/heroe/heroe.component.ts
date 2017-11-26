import { Component, OnInit } from '@angular/core';
import { ActivatedRoute} from '@angular/router'; // Tomar parametros url

import {HeroesService} from '../../servicios/heroes.service';
import {Heroe} from '../../servicios/heroes.service';

@Component({
  selector: 'app-heroe',
  templateUrl: './heroe.component.html',
  styles: []
})

export class HeroeComponent implements OnInit {

  heroe:Heroe ;


  constructor( private activatedRoute: ActivatedRoute , private srvHeroes : HeroesService)
  {
    // Obtener parametros URL. Siempre son strings
    // El param id , fue definido en app.routes.ts
    this.activatedRoute.params.subscribe (params => {
      let id =  params['id'];
      this.heroe = this.srvHeroes.getHeroe(id);
    })
  }

  ngOnInit() {
  }

}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute} from '@angular/router'; // Tomar parametros url
import { HeroesComponent} from '../heroes/heroes.component';
import { HeroesService , Heroe} from '../../servicios/heroes.service';

@Component({
  selector: 'app-heroesearch',
  templateUrl: './heroesearch.component.html',
  styles: []
})

export  class HeroesearchComponent implements OnInit {

  rerender = true;
  strHeroe : string ;
  heroComp: HeroesComponent ;
  heroes:Heroe[] = [];

  constructor( private activatedRoute: ActivatedRoute
              ,private _heroesService:HeroesService
  ) {

    this.heroComp = new HeroesComponent(_heroesService,null);



   }

  ngOnInit() {
    this.activatedRoute.params.subscribe (
      params => {
        this.strHeroe = params['strHeroe'];
      }
    );

    console.log("Heroe buscado: " + this.strHeroe);
    this.heroes = this._heroesService.buscarHeroes(this.strHeroe);
    console.log(this.heroes);
  }

}

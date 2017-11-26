import { Component, OnInit } from '@angular/core';
import { Heroe } from '../../interfaces/heroe.interface';
import { HeroesService } from '../../services/heroes.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styles: []
})
export class HeroesComponent implements OnInit {

  heroes: any = [];
  loading: boolean = true;

  constructor(private heroesSrv: HeroesService, private router: Router) { }

  ngOnInit() {
    this.heroesSrv.getAllHeroes().subscribe(data => {
      setTimeout(() => {
        this.heroes = data;
        this.loading = false;
      }, 2000);
      // Object => { Object , Object, Object}
    });
  }

  actualizar(key: any) {
    this.router.navigate(['/heroe', key]);
  }

  eliminar(key: any) {
    this.heroesSrv.deleteHeroe(key).subscribe(data => {
      delete this.heroes[key];
    })
  }


}

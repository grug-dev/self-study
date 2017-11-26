import { RouterModule, Routes } from '@angular/router';
import { HomeComponent} from './components/home/home.component';
import { AboutComponent } from './components/about/about.component';
import { HeroesComponent } from './components/heroes/heroes.component';
import { HeroeComponent } from './components/heroe/heroe.component';
import { HeroesearchComponent} from './components/heroesearch/heroesearch.component';

const APP_ROUTES: Routes = [
  // NO inician con /. Ruta Relativa. Para ser invocado, debe usarse / si se esta en otro componente.
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'heroe/:id', component: HeroeComponent },
  { path: 'heroes', component: HeroesComponent },
  { path: 'searchHeroes/:strHeroe' , component: HeroesearchComponent} ,
  { path: '**', pathMatch: 'full', redirectTo: 'home' } // Cuando no encuentre ninguna ruta.
];

// Hasthag # (Mejor mforma)  {useHash : true} lo soportan mas navegadores web
export const APP_ROUTING = RouterModule.forRoot(APP_ROUTES ,  {useHash : true });

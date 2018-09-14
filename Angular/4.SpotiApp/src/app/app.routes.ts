import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SearchComponent } from './components/search/search.component';
import { ArtistaComponent } from './components/artista/artista.component';

const APP_ROUTES: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'buscar', component: SearchComponent },
  { path: 'artista/:id', component: ArtistaComponent },
  { path: '**', pathMatch: 'full', redirectTo: 'home' }
];

// Hasthag # (Mejor mforma)  {useHash : true} lo soportan mas navegadores web
export const APP_ROUTING = RouterModule.forRoot(APP_ROUTES, { useHash: true });
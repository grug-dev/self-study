import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UsuarioComponent } from './components/usuario/usuario.component';
import { USUARIO_ROUTES } from "../app/components/usuario/usuario.routes";


const APP_ROUTES: Routes = [
  { path: 'home', component: HomeComponent },  
  { path: 'usuario/:id',
    component: UsuarioComponent, 
    children: USUARIO_ROUTES    
  },    
  { path: '**', pathMatch: 'full', redirectTo: 'home' }
];

// Hasthag # (Mejor mforma)  {useHash : true} lo soportan mas navegadores web
export const APP_ROUTING = RouterModule.forRoot(APP_ROUTES,{useHash : true});


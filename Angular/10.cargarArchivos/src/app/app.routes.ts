import { Routes, RouterModule } from '@angular/router';
import { FotosComponent } from './components/fotos/fotos.component';
import { CargaComponent } from './components/carga/carga.component';


const RUTAS: Routes = [
    {path: 'fotos', component: FotosComponent},
    {path: 'cargar', component: CargaComponent},
    {path: '**', redirectTo: 'fotos', pathMatch: 'full'}
];

export const APP_ROUTES = RouterModule.forRoot(RUTAS);


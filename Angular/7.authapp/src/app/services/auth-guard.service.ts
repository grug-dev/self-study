import { Injectable } from '@angular/core';
import { HttpModule } from '@angular/http';
import { Router , ActivatedRouteSnapshot , RouterStateSnapshot , CanActivate} from '@angular/router';
import { AuthService} from './auth.service';


@Injectable()
export class AuthGuardService implements CanActivate {

  constructor( private auth: AuthService) {

  }

  // next: Sgte pagina
  // state:
  canActivate( next: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    console.log(next);
    console.log(state);

      if (this.auth.isAuthenticated()) {
        console.log('Paso el guard');
        return true;
      }else {
        console.error('Bloqueado por el guard');
        return false;
      }
  }

}

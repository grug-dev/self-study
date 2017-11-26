import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms'
import { Heroe } from '../../interfaces/heroe.interface';
import { HeroesService } from '../../services/heroes.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-heroe',
  templateUrl: './heroe.component.html',
  styles: []
})
export class HeroeComponent implements OnInit {

  id: string;

  heroe: Heroe = {
    nombre: "",
    casa: "Marvel",
    bio: ""
  }

  nuevo: boolean = false;



  constructor(private activatedRoute: ActivatedRoute, private heroesSrv: HeroesService, private navRouter: Router) {
    this.activatedRoute.params.subscribe(parametros => {
      this.id = parametros['id'];
      this.nuevo = this.id == 'nuevo' ? true : false;
      console.log(this.nuevo);
    })
  }

  ngOnInit() {
    if (!this.nuevo) {
      this.heroesSrv.getHeroe(this.id).subscribe(data => this.heroe = data);
    }

  }

  guardarCambios(forma: NgForm) {
    console.log(this.heroe, this.nuevo);
    if (this.nuevo) {
      this.heroesSrv.insertHeroe(this.heroe).subscribe(data => {
        this.navRouter.navigate(['/heroe', data.name]);
      });
    }
    else {
      this.heroesSrv.updateHeroe(this.heroe, this.id).subscribe(data => {
        console.log("Registro Actualizado!");
      })
    }
  }

  agregarNuevo(forma: NgForm) {
    this.navRouter.navigate(['/heroe', 'nuevo']);

    forma.reset({
      casa: "Marvel"
    });

  }

}

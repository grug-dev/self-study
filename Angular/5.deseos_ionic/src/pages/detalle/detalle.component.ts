import { Component, OnInit } from '@angular/core';
import { Lista, ListaItem } from "../../app/clases/index";
// Services
import { ListaDeseosServices } from '../../app/services/lista-deseos.service';

// Capturar Parametros 
import { NavController, NavParams } from "ionic-angular";

//Controler
import { AlertController } from "ionic-angular";


@Component({
    selector: 'app-detalle',
    templateUrl: 'detalle.component.html'
})

export class DetalleComponent implements OnInit {

    idx:number;
    lista:Lista;
    item:any;

    constructor(private navCtrl:NavController, private navParams:NavParams, 
                private deseosSrv : ListaDeseosServices,
                private alertCtrl : AlertController) 
    { 
        console.log(this.navParams);
        this.idx = this.navParams.get("index");
        this.lista = this.navParams.get("lista");
        this.item = this.lista.items[this.idx];
    }

    ngOnInit() { }

    actualizar( item : ListaItem) {
        item.terminado = !item.terminado;

        let todosMarcados = true;
        for ( let item of this.lista.items) {
            if (!item.terminado) {
                todosMarcados = false;
                break;
            }
        }
        console.log(todosMarcados);
        this.lista.completado = todosMarcados;
        this.deseosSrv.saveLocalStorage();
    }

    borrarItem () {
        let confirm = this.alertCtrl.create({
            title: 'Eliminar la lista' + this.lista.nombre,
            message: 'Estás seguro de eliminar la lista',
            buttons: [
              {
                text: 'OK',
                handler: () => {
                  this.deseosSrv.eliminarLista(this.idx);
                  this.navCtrl.pop();
                }
              },              
                'Cancelar'              
            ]
          });
          confirm.present();        
    }
}
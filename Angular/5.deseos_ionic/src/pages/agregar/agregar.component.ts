import { Component, OnInit } from '@angular/core';
import { Lista, ListaItem } from '../../app/clases/index';
import { AlertController , NavController } from 'ionic-angular';
import { ListaDeseosServices } from "../../app/services/lista-deseos.service";

@Component({
    selector: 'app-agregar',
    templateUrl: 'agregar.component.html'
})

export class AgregarComponent implements OnInit {

    nombreLista:string;
    nombreItem: string = "";

    items:ListaItem[] = [];

    constructor( private alertCtrl : AlertController , private navCtrl:NavController, private lstDeseosServices:ListaDeseosServices) { }

    ngOnInit() { }

    agregar () {
        if (this.nombreItem.length == 0) {
            return;
        }

        let item = new ListaItem();
        item.nombre = this.nombreItem;
        this.items.push(item);
        this.nombreItem = "";

    }

    eliminarItem(id :number) {
        this.items.splice(id,1);
        
    }

    guardarLista() {
         if (this.nombreLista == null ||  this.nombreLista.length == 0) {
            let alert = this.alertCtrl.create({
                title: 'Nombre de la Lista',
                subTitle: 'El nombre de la lista es necesario!',
                buttons: ['OK']
              });
              alert.present();
              return;
        }

        let lista = new Lista(this.nombreLista);
        lista.items = this.items;
        this.lstDeseosServices.agregarLista(lista);
        this.navCtrl.pop();
    }
}
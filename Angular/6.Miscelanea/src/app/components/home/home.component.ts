import { Component, OnInit, OnChanges, OnDestroy , AfterContentChecked, AfterContentInit , AfterViewChecked , AfterViewInit, DoCheck } from '@angular/core';

@Component({
  selector: 'app-home',
  template: `
        <h1>Ejemplo NgStyle </h1>
        <hr>
        <app-ng-style></app-ng-style>
      
        <app-css></app-css>
      
        <p>
            Hola mundo sobre app componente
        </p>
      
        <app-clases></app-clases>
      
        <br><br>
        <h3>Directivas! </h3><hr>
        <p [appResaltado]="'orange'">
                Hola mundo
        </p>
      
        <br><br>
        <app-ng-switch></app-ng-switch>
  `,
  styles: []
})
export class HomeComponent implements OnInit, OnChanges, OnDestroy , AfterContentChecked, AfterContentInit , AfterViewChecked , AfterViewInit, DoCheck {

  constructor() { }

  ngOnInit() {
  }

  
  ngOnChanges() { console.log("ngOnChanges")}
  ngOnDestroy() { console.log("ngOnDestroy")}
  ngAfterContentChecked() { console.log("ngAfterContentChecked")}
  ngAfterContentInit() { console.log("ngAfterContentInit")}
  ngAfterViewChecked() { console.log("ngAfterViewChecked")}
  ngAfterViewInit() { console.log("ngAfterViewInit")}
  ngDoCheck() { console.log("ngDoCheck")}



}

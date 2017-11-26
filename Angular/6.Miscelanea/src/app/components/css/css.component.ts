import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-css',
  template: `
    <br><br>
    <h1>CSS Scope sobre Componentes</h1>
    <hr>
    <p> P sobre css component</p>
  `,
  styles: [
    `
      p {
        color: red;
        font-size: 20px;
      }
    `
  ]
})
export class CssComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}

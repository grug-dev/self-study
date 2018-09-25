import React, { Component } from 'react';
//import logo from './logo.svg';
import './App.css';

var itinerario = [
  { id: 1,  ciudad: 'Barcelona', dias: 3 },
  { id: 2, ciudad: 'Madrid', dias: 5 },
  { id: 3, ciudad: 'Paris', dias: 2 } , 
  { id: 4,  ciudad: 'Barcelona', dias: 2 },
];

class App extends Component {
  
  render() {
    return React.createElement('ul', null,
        itinerario.map(function(item) {
         return React.createElement(
                        'li', 
                        { key : item.id},
                        'Ciudad ', item.ciudad, ', quedándonos ', item.dias, ' días'
      );
    })
  );
  }
}

export default App;

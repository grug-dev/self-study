import React, { Component } from 'react';
import './App.css';
import ValidationComponent from './components/task2/ValidationComponent';
import CharComponent from './components/task2/CharComponent';



class App extends Component {

  state = {
    length : '',
    currentValue : ''
  }
  
  render() {

    let mycharacters ;

    const currentValue = this.state.currentValue;
    if (currentValue.length > 0) {
      mycharacters = currentValue.split('').map( (l , index) => {
        console.log(index, l);
        return <CharComponent letter={l}
         key={index}
         deleteCharacter={ () => this.deleteCharacter(index)}
         />         
      });    
    }    
    

    return(
      <div className="myApp">
        <input type="text" onChange={this.changeInputHandler} value={this.state.currentValue} /> 
        <label><p>{this.state.length}</p></label>
        <ValidationComponent  length = {this.state.length}/>    
        { mycharacters }
      </div>      
    );
  }

  deleteCharacter =  (index) => {
    const X = this.state.currentValue.split('');
    X.splice(index , 1);
    const text = X.join('');
    const length = text.length;
    this.setState({
      currentValue : text ,
      length : length
    }
      
    );
  }

  changeInputHandler = (event) => {
    const currentValue = event.target.value;
    let length = '';
    if ( currentValue){
      length = event.target.value.length;      
    }
      
    this.setState({
      length : length ,
      currentValue : currentValue
    });
  }


}

export default App;

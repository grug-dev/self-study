import React, {Component} from "react";
import "./App.css";
import Person from "./Person/Person";


class App extends Component {

    state = {
        persons: [
            {
                id : 1,
                name: 'Max',
                age: 28
            }, 
            {
                id : 2,
                name: 'Cristian',
                age: 29
            }
        ],
        showPerson: false
    }

    render() {
        let mypersons;

        const inlineStyle = {
            backgroundColor: 'green',
            color: 'white',
            font: 'inherit',
            border: '1x solid blue',
            padding: '8px',
            cursor: 'pointer' ,            
        };

        if (this.state.showPerson) {
            mypersons = (
                this.state.persons.map( (p , index ) => {
                    return (
                        <Person key={p.id}
                         name={p.name} 
                         deletePersonHandler={this.deletePersonHandler}
                         changed = { (event) => this.nameChangedHandler( event , p.id)}
                          />
                    )
                })
            );    
            inlineStyle.backgroundColor = 'red';

            inlineStyle[':hover'] = {
                backgroundColor : 'salmon' ,
                color: 'black'
            }
        }

        let classes = ['red' , 'bold'].join(' ');

        return (            
                <div className="App">
                <p className={this.state.showPerson ? classes : ''}>Hi React</p>
                    <button style={inlineStyle} onClick={this.showPersonHandler}>
                        Show Persons
                    </button>
                    {mypersons}
                </div>            
        );
    }

    nameChangedHandler = (event , id) => {
        const personIndex = this.state.persons.findIndex( person => {
            return person.id === id;
        });

        const person = { 
            ...this.state.persons[personIndex]
        };

        person.name =  event.target.value;

        const persons = [...this.state.persons];
        persons[personIndex] = person;

        this.setState({
            persons : persons
        });
    }

    showPersonHandler = (event) => {
        const currentShowPersons = this.state.showPerson;
        console.log("Clicked! ");
        this.setState({
            showPerson: !currentShowPersons
        });
    }

    deletePersonHandler = ( index) => {
        const persons = [...this.state.persons];
        persons.splice( index , 1 ) ;
        this.setState({
            persons : persons
        });
    }
}

export default (App);
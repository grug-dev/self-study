import React, {Component} from "react";
import Persons from "../components/Persons/Persons";
import myClasses from './App.css';
import WithClass from '../components/hoc/WithClass';
import withWrapped from "../components/hoc/withWrapped";

export const AuthContext = React.createContext(false);

class App extends Component {

    constructor( props) {
        super(props);

        this.state = {
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
            showPerson: false ,
            toogleClickCounter : 0 ,
            isAuthenticated : false
        }
    }

   

    render() {
        let mypersons;
        let btnClass = '';
       
        if (this.state.showPerson) {
            mypersons = (
                <Persons 
                persons = {this.state.persons}
                deletePersonHandler={this.deletePersonHandler}
                nameChangedHandler={this.nameChangedHandler}
                isAuthenticated = {this.state.isAuthenticated}
                />
            );    
            btnClass = myClasses.Red;
        }

        let classes = ['red' , 'bold'].join(' ');


        return (   
            <WithClass className={myClasses.App}>                                                          
                <p className={this.state.showPerson ? classes : ''}>Hi React</p>
                    <button  
                        className={btnClass}
                        onClick={this.showPersonHandler}>
                        Show Persons
                    </button>
                    <button
                        onClick={this.loginHandler}
                    >
                        Log In
                    </button>
                    <AuthContext.Provider value={this.state.isAuthenticated}>
                        {mypersons}
                    </AuthContext.Provider>
                           
            </WithClass>
        );
    }

    loginHandler = () => {
        this.setState({
            isAuthenticated: true
        });
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
               
        this.setState (  (prevState , props) => {
            return {
                showPerson: !prevState.showPerson,
                toogleClickCounter : prevState.toogleClickCounter + 1
            }            
        });
    }

    deletePersonHandler = ( index) => {
        const persons = [...this.state.persons];
        persons.splice( index , 1 ) ;
        this.setState({
            persons : persons
        });
    }


    static getDerivedStateFromProps(nextProps, prevState) {
        console.log('getDerivedStateFromProps', prevState , nextProps);

        return prevState;
    }
}

export default  withWrapped(App , myClasses.App);
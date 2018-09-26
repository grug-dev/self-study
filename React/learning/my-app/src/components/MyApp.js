import React , { Component} from 'react';
import Table from './Table';
import Form from './Form';
import UserInput from './UserInput';
import UserOutput from './UserOutput';

class MyApp extends Component {

    state = {
        characters : [] ,
        userName : 'Cristian Initial'
    };

    render() {
        return(
            <div className="myAppContainer">
                <Table  
                    data={this.state.characters}
                    removeCharacter={this.removeCharacter}                
                />
                <Form  handleSubmit={this.handleSubmit}/>
                <UserInput
                 handleUserNameChanged={this.handleUserNameChanged}
                 userName={this.state.userName}
                 />
                <UserOutput userName={this.state.userName}/>
            </div>
        );
    }

    handleUserNameChanged = (event) => {
        this.setState({
            userName : event.target.value
        });
    };

    removeCharacter = ( index ) => {       
        const { characters } = this.state;
        
        this.setState(
            {
                characters : characters.filter ( ( character, i) => i !== index)
            }
        );

    };

    handleSubmit = (character) => {
        this.setState({
            characters : [...this.state.characters , character]
        });
    }
}

export default MyApp;
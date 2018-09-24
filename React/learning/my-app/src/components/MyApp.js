import React , { Component} from 'react';
import Table from './Table';
import Form from './Form';

class MyApp extends Component {

    state = {
        characters : []
    };

    render() {
        return(
            <div className="myAppContainer">
                <Table  
                    data={this.state.characters}
                    removeCharacter={this.removeCharacter}                
                />
                <Form  handleSubmit={this.handleSubmit}/>
            </div>
        );
    }

    ;

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
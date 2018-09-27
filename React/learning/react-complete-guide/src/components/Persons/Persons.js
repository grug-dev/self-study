import React, { Component } from 'react'
import Person from './Person/Person';

class Persons extends Component {

    constructor (props) {
        super(props);
        this.lastPersonRef = React.createRef();
    }

    componentDidMount() {
        this.lastPersonRef.current.myfocus();
    }

    render() {
        
            return this.props.persons.map( (p , index ) => {
                return (                        
                        <Person  key={p.id}
                        name={p.name} 
                        deletePersonHandler={ this.props.deletePersonHandler}
                        changed = { 
                            (event) => this.props.nameChangedHandler( event , p.id)
                        }
                        ref={this.lastPersonRef}                        
                        />                        
                );
            })
        
        };
    
}




export default Persons ;
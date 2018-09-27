import React , { Component} from 'react';
import classes from './Person.css';
import PropTypes from 'prop-types';
import withWrapped from '../../hoc/withWrapped';
import { AuthContext } from '../../../containers/App';




class Person extends Component {


constructor(props) {
    super(props); 
    this.myInput = React.createRef();    
}

componentDidMount() {
    this.myInput.current.focus();
}

myfocus() {
    this.myInput.current.focus();
}

    render() {
    
        return (        
            <div className={classes.Person}>
            
                 <AuthContext.Consumer>
                     {auth => auth ? <p>I'm authenticated using AuthContext</p> : null}
                 </AuthContext.Consumer>

                <p className={classes.PersonClicked}
                 onClick={this.props.deletePersonHandler}>
                 I'm a {this.props.name} !</p>
                <p>{ this.props.children}</p>
                <input type="text" 
                        ref={this.myInput}
                        onChange={ this.props.changed} 
                        defaultValue={ this.props.name} >
                </input>                                
            </div>
        );
    }
}


// PropTypes

Person.propTypes = {
    name: PropTypes.string.isRequired,
    changed: PropTypes.func.isRequired,
    deletePersonHandler: PropTypes.func.isRequired
}


export default withWrapped(Person , classes.Person);
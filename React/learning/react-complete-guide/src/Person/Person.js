import React from 'react';
import './Person.css';

const person = (props) => {



    return (
        <div className="Person">
            <p className="PersonClicked"             
             onClick={props.deletePersonHandler}>
             I'm a {props.name} !</p>
            <p>{props.children}</p>
            <input type="text" onChange={props.changed} defaultValue={props.name} ></input>
        </div>
    );
}

export default person;
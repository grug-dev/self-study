import React from 'react';

const charStyle = {
    display : 'inline-block',
    padding: '16px',
    textAlign: 'center',
    margin: '16px',
    border: '1px solid'

}

const charComponent = (props) => {
    return(
        <div style={charStyle}>
           <p onClick={props.deleteCharacter}>{props.letter}</p>
        </div>        
    );
}

export default charComponent ;
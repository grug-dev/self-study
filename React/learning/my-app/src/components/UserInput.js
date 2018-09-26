import React from 'react';

const userInput = (props) => {
    return (
        <div className="userInput">
        <hr></hr>
            <label>Input User</label>
            <input type="text" name="myInput" value={props.userName} onChange={props.handleUserNameChanged}></input>
        </div>
    );
};

export default userInput;
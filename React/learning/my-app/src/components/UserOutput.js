import React from 'react';

const userOutput = (props) => {
    return(
        <div>
            <p id="firstP" key="firstP"> My First {props.userName} </p>
            <p id="secondP" key="secondP"> My Second: Parragraph</p>
        </div>
    );
};

export default userOutput;
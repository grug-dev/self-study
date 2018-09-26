import React from 'react'

const validationComponent = (props) => {
    const length = props.length ;
    let result = null;

    if ( length > 0)
    result = length <= 5 ? <p>Text too short</p> : <p>Text long enough</p>

    return (
        <div className="validationClass">
        <hr/>
            {result}
        </div>
    );

}

export default validationComponent;
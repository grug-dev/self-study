import  React , { Component}  from 'react';


class Contador extends Component {

    render() {        
        return React.createElement('div' , null , 
                React.createElement('p' , null , ' La cuenta es : ' , this.props.count) ,
                // Incrementar
                React.createElement('input' , {
                    type: 'button' ,
                    value: 'Incrementar',
                    onClick: this.props.onIncrementar
                }),
                // Decrementar
                React.createElement ('input', {
                    type: 'button',
                    value: 'Decrementar' ,
                    onClick: this.props.onDecrementar
                })
        ) ;
    }
}

export default Contador;
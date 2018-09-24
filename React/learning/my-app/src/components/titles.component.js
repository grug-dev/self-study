import  React , { Component}  from 'react';
import Contador from './contador.component';
import ReactDOM from 'react-dom';


class Titles extends Component {

    render() {
        var app = React.createElement('div' , null , 
                        buildEncabezado( { 
                            pageName: ' Mi Primera Pagina'
                        }), 
                        buildMenu() , 
                        buildContent ( {
                            children: React.createElement ('p' , null , 'Mi Contenido')
                        }) ,
                        React.createElement('div' , { className : 'myCounter'} , buildContador (0 , 'myCounter'))
        );
        return app;
    }

}

function buildEncabezado( props) {
    return 
        React.createElement('h1' , null , 'Mi Sitio - ' + props.pageName);
}

function buildMenu ( ) {
    return React.createElement ( 'ul' , { className: 'menu'} , 
                                React.createElement ('li' , null, 'Inicio') ,
                                React.createElement ('li' , null, 'Quienes Somos') ,
                                React.createElement ('li' , null, 'FAQ') 
    )
}


function buildContent(props) {
    return React.createElement( 'div' , { className: 'contenido'} , props.children);
}

function buildContador( tmpCount , containerName) {
    return     
    React.createElement (Contador ,{
        count : tmpCount ,
        onIncrementar : function() {                        
            ReactDOM.render (buildContador(tmpCount + 1 , containerName) , document.getElementById(containerName));
        },
        onDecrementar: function() {                        
            ReactDOM.render (buildContador(tmpCount - 1 , containerName) , document.getElementById(containerName));
        }
    }) 
}

export default Titles;
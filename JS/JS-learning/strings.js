var cadena = "Hello World";
var cadena2 = "hola";

console.log( cadena2.indexOf(cadena[0]) == 0);



function nextChar( letter) {
    return fromCharCode( next ( toCharCode( letter) ) ) ;
}

function fromCharCode ( charCode ) {
    return String.fromCharCode ( charCode );
}


function next ( charCode ) {
    return charCode + 1 ;
}

function toCharCode ( letter ) {
      return letter.charCodeAt(0);
}

console.log(nextChar('b')) ;
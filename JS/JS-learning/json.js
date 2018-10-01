let objetoJS = {
    nombre: "Cristian" , 
    edad: 30 ,
    imprimir : function() {
        return "Hola"
    }
}

let myObjectAsString = JSON.stringify(objetoJS);
console.log(myObjectAsString, objetoJS.imprimir());

console.log("ToJson" , JSON.parse(myObjectAsString));
// BIND Function
let carro = {
    color: "Blanco",
    marca: "mazda",
    imprimir:function (){
        let salida = this.marca + " - " + (this.color);
        return salida;
    }
}
console.log(carro.imprimir() );

var logCarro =  function (arg1, arg2) {
    console.log("Bind Carro:" , this.imprimir ());
    console.log("Argumentos.." , arg1, arg2);
}
var logModeloCarro = logCarro.bind(carro);
logModeloCarro(2,4);

// Call
// carro is the object of the this.
// 2,4 are argumetns.
logCarro.call(carro , 2 , 4);

// Apply
//  Just 2 arguments.: This object and an array.
//  It's used when we dont know the amount of the parameters of the function.
logCarro.apply( carro, [2,4]);


let myObject = {
    name : "Cristian",
    age: 30,
    hobbies: [
        "soccer",
        "programming"
    ],
    print : function () {
        console.log(this.name, this.age, this.hobbies)
    }
}

myObject.print();


// Copy the object but the array is still pointer the same array by reference
let cloneObject = {
    ...myObject,
    name: "Camilo",
    age: 33
}

// Copy object and array.
let realCloneObject = {
    ...myObject,
    name: " Cristian Camilo",
    age: 33,
    hobbies : [...cloneObject.hobbies]
}

myObject.hobbies.push("videogames");
cloneObject.print();
realCloneObject.print();



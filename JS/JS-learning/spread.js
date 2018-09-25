sum = (x, y, z) => x + y + z;

let numbers = [1, 3];

numbers = [...numbers, 4, 5]

console.log(sum(...numbers));

// Spread oBJECT
let obj1 = {
    name: 'Cristian'
};
let obj2 = {
    name: 'Camilo'
};
let obj3 = {
    ...obj2,
    age: 29
};

createArrayObjets = (...objs) => {
    let myobjects = [];
    myobjects = objs;
    return myobjects;
}


let myobjects = createArrayObjets(obj1, obj2, obj3);
console.log(myobjects);
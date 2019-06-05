"use strict";
console.log("Dynamic Cols");
function getProperty(objToFindProperty, keyToFind) {
    let value;
    let key = keyToFind;
    let obj = objToFindProperty;
    let splitKey = keyToFind.toString().split("\.");
    for (let keyId = 0; keyId < splitKey.length - 1; keyId++) {
        obj = obj[splitKey[keyId]];
    }
    value = obj[splitKey[splitKey.length - 1]];
    if (value === undefined) {
        value = "---";
    }
    console.log(key, value);
    return value;
}
const cristian = {
    id: 23,
    name: "Cristian",
    age: 30,
    address: {
        name: "Calle 70 SUr",
        city: "Bogota",
        contact: {
            mobile: 3014844557
        }
    }
};
let cols;
let rows;
cols = [
    { name: "id" },
    { name: "name" },
    { name: "age" },
    { name: "address.city" },
    { name: "address.contact.mobile" },
    { name: "address.contact.mob2ile" }
];
const colsObject = ["id", "name", "age"];
cols.forEach(col => {
    getProperty(cristian, col.name);
});

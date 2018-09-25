// Passed by Reference
let person = {
    name: 'Cristian'
}
const myPerson = person;
person.name = ' Camilo ';
console.log(myPerson); // Will Print Camilo


// Creating a Copy
const copyPerson = {
    ...person
}
person.name = '  Another Guy! ';
console.log(copyPerson); // Will Prnt Camilo.
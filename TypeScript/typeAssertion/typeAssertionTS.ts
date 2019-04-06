/**
 * TypeScript allows changing a variable from one type to another. TypeScript refers to this process as Type Assertion. The syntax is to put the target type between < > symbols and place it in front of the variable or expression. The following example explains this concept
 */
let str = "1";
let strInNumber: number = <number>(<any>str);
console.log(strInNumber);

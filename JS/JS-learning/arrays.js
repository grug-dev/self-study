const numbers = [1, 2, 3, 4];


console.log(numbers.reduce((a, b) => a + b)); // 1 + 2 + 3 + 4
console.log(numbers.reduce((a, b) => a + b, 10)); // 10 + 1 + 2 + 3 + 4
console.log(numbers.reduce((a, b) => Math.max(a, b))); // 4
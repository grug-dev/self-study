interface Person {
  id: number;
  name: string;
  age: number;
}

let students: Array<Person> = [
  { id: 1, name: "Cristian", age: 33 },
  { id: 2, name: "Bibiana", age: 33 },
  { id: 3, name: "Marlen", age: 33 }
];

const nacho: Person = {
  id: 99,
  name: "nacho",
  age: 34
};

students.push(nacho);

students
  .filter(s => s.age > 33)
  .map(s => s.name)
  .forEach(s => {
    console.log(s);
  });

const sumAges = (students: Array<Person>) => {
  const totalAges = students
    .map(s => s.age)
    .reduce(function(a, b) {
      return a + b;
    });
  console.log(totalAges);
};

sumAges(students);




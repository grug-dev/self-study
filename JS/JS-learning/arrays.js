const numbers = [1, 2, 3, 4];

console.log(numbers.slice(3));

console.log(numbers.reduce((a, b) => a + b)); // 1 + 2 + 3 + 4 = 10
console.log(numbers.reduce((a, b) => a + b, 10)); // 10 + 1 + 2 + 3 + 4 = 20
console.log(numbers.reduce((a, b) => Math.max(a, b))); // 4

console.log(numbers.join(" - "));

const data = [
  {
    id: "1",
    userName: "Mike",
    email: "mike@sss.com"
  },
  {
    id: "2",
    userName: "John",
    email: "john@sss.com"
  }
];

const addKey = (item, index) => {
  const newItem = {
    ...item,
    key: item.id
  };
  return newItem;
};

const findAndUpdate = data => {
  // Find & Update Item
  const indexFound = data.findIndex(p => {
    return p.id == 1;
  });
  const personToUpdate = {
    ...data[indexFound]
  };
  personToUpdate.userName = "userNameUpdated";
  const newData = [...data];
  newData[indexFound] = personToUpdate;

  return newData;
};

console.log("Data with new key attribute", data.map(addKey));
console.log("Data Updated: ", findAndUpdate(data));

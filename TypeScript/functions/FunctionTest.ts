const optionalParameters = (x: number, y: number = 3, z?: number) => {
  let sum = x + y;
  if (z) sum += z;
  console.log(sum);
};

optionalParameters(1);

"use strict";
const optionalParameters = (x, y = 3, z) => {
    let sum = x + y;
    if (z)
        sum += z;
    console.log(sum);
};
optionalParameters(1);

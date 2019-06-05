"use strict";
var CategoryType;
(function (CategoryType) {
    CategoryType[CategoryType["EXPENSE"] = 0] = "EXPENSE";
    CategoryType[CategoryType["EXCLUDE"] = 1] = "EXCLUDE";
    CategoryType[CategoryType["TRANSFER"] = 2] = "TRANSFER";
})(CategoryType || (CategoryType = {}));
let transactions = [
    { id: 1, category: CategoryType.EXCLUDE },
    { id: 2, category: CategoryType.EXPENSE },
    { id: 3, category: CategoryType.TRANSFER },
    { id: 4, category: CategoryType.EXPENSE }
];

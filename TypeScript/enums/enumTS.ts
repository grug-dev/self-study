enum CategoryType {
  EXPENSE,
  EXCLUDE,
  TRANSFER
}

let transactions = [
  { id: 1, category: CategoryType.EXCLUDE },
  { id: 2, category: CategoryType.EXPENSE },
  { id: 3, category: CategoryType.TRANSFER },
  { id: 4, category: CategoryType.EXPENSE }
];

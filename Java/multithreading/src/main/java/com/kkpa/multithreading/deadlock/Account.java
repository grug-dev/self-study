package com.kkpa.multithreading.deadlock;

public class Account {

  public int balance = 10000;

  public void deposit(int amount) {
    balance += amount;
  }

  public void withDraw(int amount) {
    balance -= amount;
  }

  public void transfer(Account acc2, int amount) {
    withDraw(amount);
    acc2.deposit(amount);
  }

}

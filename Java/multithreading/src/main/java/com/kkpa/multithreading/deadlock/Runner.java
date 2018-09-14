package com.kkpa.multithreading.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Runner {

  Account acc1 = new Account();
  Account acc2 = new Account();
  Random random = new Random();
  Lock lock1 = new ReentrantLock();
  Lock lock2 = new ReentrantLock();
  private int contBothLocked = 0;

  private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {

    boolean gotFirstLock = false;
    boolean gotSecondLock = false;

    try {
      gotFirstLock = firstLock.tryLock();
      gotSecondLock = secondLock.tryLock();
    } finally {
      if (gotFirstLock && gotSecondLock) {
        contBothLocked++;
        System.out.println("Both Locked: " + contBothLocked);
        return;
      }

      if (gotFirstLock) {
        System.out.println("First Locked - Unlocked");
        firstLock.unlock();
      }

      if (gotSecondLock) {
        System.out.println("Second Locked - Unlocked");
        secondLock.unlock();
      }
    }

  }

  public void firstThread() throws InterruptedException {

    IntStream.range(0, 1000).forEach(a -> {
      try {
        acquireLocks(lock1, lock2);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      try {
        acc1.transfer(acc2, 10);
      } finally {
        lock2.unlock();
        lock1.unlock();
      }
    });
  }

  public void secondThread() throws InterruptedException {
    IntStream.range(0, 1000).forEach(a -> {

      try {
        acquireLocks(lock2, lock1);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      try {
        acc2.transfer(acc1, 10);
      } finally {
        lock2.unlock();
        lock1.unlock();
      }
    });
  }

  public void finished() {
    System.out.println("Account 1 Balance: " + acc1.balance + "\t" + "Account 2 Balance: "
        + acc2.balance + " Total Balance: " + (acc1.balance + acc2.balance));

  }
}

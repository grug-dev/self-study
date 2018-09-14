package com.kkpa.multithreading.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Runner {

  private int cont = 0;

  private Lock lock = new ReentrantLock();


  private void increment() throws InterruptedException {
    lock.lock();

    try {
      cont += IntStream.range(0, 10000).count();
    } finally {
      lock.unlock();
    }
  }

  public void firstThread() throws InterruptedException {
    increment();
    System.out.println("Incremented by First Thread: " + Thread.currentThread().getName());
  }

  public void secondThread() throws InterruptedException {
    System.out.println("Incremented by Second Thread " + Thread.currentThread().getName());
    increment();
  }


  public int getCont() {
    return this.cont;
  }
}

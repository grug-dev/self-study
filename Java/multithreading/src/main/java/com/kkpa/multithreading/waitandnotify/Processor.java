package com.kkpa.multithreading.waitandnotify;

public class Processor {

  private volatile int cont = 0;

  public void produce() throws InterruptedException {
    synchronized (this) {
      cont++;
      System.out.println("Producer Running " + cont);
      wait();
      System.out.println("End PRoduce" + cont);
    }
  }

  public void consume() throws InterruptedException {
    Thread.sleep(10000l);
    synchronized (this) {
      cont--;
      System.out.println("Consumer Running " + cont);
      notify();
      System.out.println("Notify sended by consumer" + cont);
    }
  }

}

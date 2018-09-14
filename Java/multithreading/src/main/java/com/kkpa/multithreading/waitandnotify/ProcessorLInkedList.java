package com.kkpa.multithreading.waitandnotify;

import java.util.LinkedList;

public class ProcessorLInkedList {

  LinkedList<Integer> lst = new LinkedList<>();

  private static final int LIMIT = 5;

  private Object lock = new Object();

  private volatile int value = 0;

  public void produce() throws InterruptedException {

    synchronized (lock) {
      while (lst.size() == LIMIT) {
        lock.wait();
      }
      lst.add(++value);
      System.out.println(value + " was added.  Lst size: " + lst.size());
      lock.notify();
    }

  }

  public void consume() throws InterruptedException {
    synchronized (lock) {
      while (lst.size() == 0) {
        lock.wait();
      }
      Integer removed = lst.removeFirst();
      System.out.println(removed + " was removed. Lst size: " + lst.size());
      lock.notifyAll();
    }

  }

}

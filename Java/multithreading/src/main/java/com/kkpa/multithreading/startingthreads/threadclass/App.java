package com.kkpa.multithreading.startingthreads.threadclass;

public class App {

  public static void main(String[] args) {
    Runner r1 = new Runner("Thread 1");
    Runner r2 = new Runner("Thread 2");

    r1.start();
    r2.start();

  }

}

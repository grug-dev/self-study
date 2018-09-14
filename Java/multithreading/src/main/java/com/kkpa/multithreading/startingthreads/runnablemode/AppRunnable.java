package com.kkpa.multithreading.startingthreads.runnablemode;

import java.util.stream.IntStream;

public class AppRunnable {

  public static void main(String[] args) {
    RunnerRunnable r1 = new RunnerRunnable();
    RunnerRunnable r2 = new RunnerRunnable();

    Runnable r3 = () -> {
      IntStream.range(0, 10).forEach(n -> {
        System.out.println("Hello Lambad " + n + " - ");
      });
    };
    Thread t1 = new Thread(r1);
    Thread t2 = new Thread(r2);
    Thread t3 = new Thread(r3);

    t1.start();
    t2.start();
    t3.start();
  }

}

package com.kkpa.multithreading.reentrantlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppReentrantLock {


  public static void main(String[] args) throws InterruptedException {
    Runner runner = new Runner();
    Runnable r1 = () -> {
      try {
        runner.firstThread();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    };
    Runnable r2 = () -> {
      try {
        runner.secondThread();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    };

    ExecutorService executor = Executors.newFixedThreadPool(10);
    executor.submit(r1);
    executor.submit(r2);
    executor.submit(r2);
    executor.submit(r2);
    executor.shutdown();
    executor.awaitTermination(5, TimeUnit.SECONDS);

    System.out.println("Sum: " + runner.getCont());
  }

}

package com.kkpa.multithreading.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class Processor implements Runnable {

  private int id;

  Processor(int pId) {
    this.id = pId;
  }

  public void run() {
    System.out.println("Strating: " + id);

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    System.out.println("Completed: " + id);
  }
}


public class AppThreadPool {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    ExecutorService executor = Executors.newFixedThreadPool(2);

    IntStream.range(0, 5).forEach(i -> {
      executor.submit(new Processor(i));
    });

    executor.shutdown();

    System.out.println("All Tasks Submitted");

    try {
      executor.awaitTermination(2, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    long end = System.currentTimeMillis();
    System.out.println("All Tasks Completed" + " - " + (end - start) + " ms");

  }

}

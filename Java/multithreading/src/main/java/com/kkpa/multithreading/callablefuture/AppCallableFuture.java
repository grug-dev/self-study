package com.kkpa.multithreading.callablefuture;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AppCallableFuture {

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executor = Executors.newCachedThreadPool();

    Callable<Integer> callable = () -> {
      System.out.println("Starting...");
      Random random = new Random();
      int duration = random.nextInt(4000);

      System.out.println("Sleeping: " + duration);
      if (duration > 2000) {
        throw new IOException("Sleeping too long!");
      }

      try {
        Thread.sleep(duration);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      return duration;
    };

    Future<Integer> future = executor.submit(callable);
    executor.shutdown();
    executor.awaitTermination(10, TimeUnit.SECONDS);

    try {
      System.out.println("Future Value: \t" + future.get());
    } catch (ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}

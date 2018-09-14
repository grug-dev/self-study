package com.kkpa.multithreading.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class AppSemaphore {


  public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();

    Runnable r = () -> {
      try {
        Connection.getInstance().connect();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    };

    IntStream.range(0, 10).forEach(i -> {
      executor.submit(r);
    });

    executor.shutdown();

  }

}

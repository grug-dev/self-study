package com.kkpa.multithreading.interruptingthreads;

import java.util.Random;

public class AppMultithreading {

  public static void main(String[] args) throws InterruptedException {
    Runnable run = () -> {
      System.out.println("Runnable Initialized");
      Random ran = new Random();
      long cont = 0;
      for (int i = 0; i < 1E8; i++) {
        cont++;
        if (Thread.currentThread().isInterrupted()) {
          System.out.println("I have interrupted by other!");
          break;
        }
        Math.sin(ran.nextDouble());
      }
      System.out.println("Runnable finished: " + cont);
    };

    Thread t1 = new Thread(run);
    t1.start();

    Thread.sleep(2000);
    System.out.println("Interrupting..");
    t1.interrupt();

    System.out.println("Join");
    t1.join();
    System.out.println("Joined");

    System.out.println("App Finished");
  }

}

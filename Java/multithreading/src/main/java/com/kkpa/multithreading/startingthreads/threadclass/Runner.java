package com.kkpa.multithreading.startingthreads.threadclass;

import java.util.stream.IntStream;

public class Runner extends Thread {

  public Runner(String name) {
    super(name);
  }

  @Override
  public void run() {

    IntStream.range(0, 10).forEach(n -> {
      System.out.println("Hello " + n + " - " + this.getName());
    });

  }

}

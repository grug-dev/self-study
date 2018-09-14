package com.kkpa.multithreading.producerconsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class AppProducerConsumer {

  static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(15);

  public static void main(String[] args) {
    Runnable runProducer = () -> {
      try {
        producer();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    };
    Runnable runConsumer = () -> {
      try {
        consumer();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    };

    ExecutorService executor = Executors.newFixedThreadPool(5);
    IntStream.range(0, 5).forEach(d -> executor.submit(runProducer));

    ExecutorService executorR = Executors.newFixedThreadPool(5);
    IntStream.range(0, 10).forEach(d -> executorR.submit(runConsumer));

    IntStream.range(20, 25).forEach(d -> executor.submit(runProducer));

    executor.shutdown();
    executorR.shutdown();
  }

  public static void producer() throws InterruptedException {
    Random random = new Random();
    Integer number = random.nextInt();
    System.out.println("Put Queue: " + number);
    queue.put(number);
  }


  public static void consumer() throws InterruptedException {
    Integer numTaken = queue.take();
    System.out.println("Num Taken: " + numTaken);
  }

}

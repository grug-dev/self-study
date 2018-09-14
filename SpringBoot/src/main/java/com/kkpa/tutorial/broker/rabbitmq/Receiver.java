package com.kkpa.tutorial.broker.rabbitmq;

import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

  private CountDownLatch latch = new CountDownLatch(1);

  public void receiveMessage(String message) {
    System.out.println("Received <" + message + ">" + latch.getCount());
    latch.countDown();
    System.out.println(String.format("Count down: %d", latch.getCount()));
  }

  public CountDownLatch getLatch() {
    return latch;
  }

}

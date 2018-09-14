package com.kkpa.multithreading.semaphores;

import java.util.concurrent.Semaphore;

public class Connection {

  private static Connection connection = new Connection();

  private int connections = 0;

  private Semaphore semaphore = new Semaphore(5, true);

  private Connection() {

  }

  public void connect() throws InterruptedException {

    try {
      semaphore.acquire();
      doConnect();
    } finally {
      releaseConnect();
      semaphore.release();
      System.out.println(
          "Number of connections: " + connections + " - " + Thread.currentThread().getName());
    }
  }

  private void doConnect() {
    synchronized (this) {
      connections++;

    }
  }

  private void releaseConnect() throws InterruptedException {
    Thread.sleep(2000l);
    synchronized (this) {
      connections--;
    }

  }

  public static Connection getInstance() {
    return connection;
  }

}

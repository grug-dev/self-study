package com.kkpa.multithreading.basicsynchronization;

class Procesor extends Thread {

  private volatile boolean running = true;

  private volatile int cont = 0;

  public Procesor(String name) {
    super(name);
  }

  @Override
  public void run() {
    // TODO Auto-generated method stub
    super.run();

    while (running) {
      cont++;
      System.out.println("Hello: " + cont + " - " + this.getName());
    }
  }

  public void shutDown() {
    this.running = false;
  }

}


public class App {

  public static void main(String[] args) {
    Procesor t1 = new Procesor("t1");
    Procesor t2 = new Procesor("t2");

    t1.start();
    t2.start();


    try {
      Thread.sleep(100l);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    t1.shutDown();
    System.out.println("T1 Closed");

    t2.shutDown();
    System.out.println("T2 Closed");
  }

}

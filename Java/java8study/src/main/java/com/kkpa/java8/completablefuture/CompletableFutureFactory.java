package com.kkpa.java8.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureFactory {

  public static final String CRISTIAN = "CRISTIAN";

  private static final String ALEJANDRA = "ALEJANDRA";

  private static final long TWO_SECONDS = 2000l;

  private static final int ONE_THOUSAND = 1000;

  public CompletableFuture<Integer> findAgeByPerson(String person) {
    return CompletableFuture.supplyAsync(() -> {
      System.out.println("Finding Age To: " + person);
      sleep(TWO_SECONDS);
      int ageFound = -1;
      switch (person) {
        case CRISTIAN:
          ageFound = 32;
        case ALEJANDRA:
          ageFound = 28;
        default:
          ageFound = 0;
      }
      return ageFound;
    });
  }

  private void sleep(long miliSeconds) {
    try {
      Thread.sleep(miliSeconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public CompletableFuture<String> createACompletableFuture(String msg) {
    return CompletableFuture.completedFuture(msg);
  }

  public CompletableFuture<Void> runningSimpleAsyncStage() {
    CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
      sleep(TWO_SECONDS);
      System.out.println("isDaemon: " + Thread.currentThread().isDaemon());
      System.out.println("Finish SImple Async ");
    });

    return cf;
  }

  public CompletableFuture<String> transformToUpperCaseAsync(String message, int timeDelayed) {

    CompletableFuture<String> cf =
        CompletableFuture.completedFuture(message).thenApplyAsync(String::toUpperCase);
    sleep(timeDelayed * ONE_THOUSAND);

    return cf;
  }

  public CompletableFuture<String> transformToLowerCaseAsync(String message, int timeDelayed) {

    CompletableFuture<String> cf =
        createACompletableFuture(message).thenApplyAsync(String::toLowerCase);
    sleep(timeDelayed * ONE_THOUSAND);

    return cf;
  }

}

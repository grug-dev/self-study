package com.kkpa.java8.threads;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.concurrent.CompletableFuture;
import org.junit.Before;
import org.junit.Test;
import com.kkpa.java8.completablefuture.CompletableFutureFactory;

public class TestCompletableFuture {

  private CompletableFutureFactory competableFutureFactory;

  private static final String ANOTHER_MESSAGE = "Another Message";
  private static final String MESSAGE = "message";
  private static final int ONE_SECOND = 1;

  @Before
  public void setup() {
    competableFutureFactory = new CompletableFutureFactory();
  }

  private void log(String msg) {
    System.out.println(msg);
  }

  private void sleep(long miliSeconds) {
    try {
      Thread.sleep(miliSeconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testCreateACompletableFuture() {
    String message = "message";

    CompletableFuture compTableFutureTest =
        competableFutureFactory.createACompletableFuture(message);

    assertTrue(compTableFutureTest.isDone());

    assertEquals(message, compTableFutureTest.getNow("No Message"));
  }

  @Test
  public void testRunningSimpleAsynchronousStage() {
    CompletableFuture compTableFutureTest = competableFutureFactory.runningSimpleAsyncStage();
    assertFalse(compTableFutureTest.isDone());
    sleep(3000l);
    assertTrue(compTableFutureTest.isDone());
  }


  @Test
  public void testThenApply() {
    String msgToTest = "message";
    log("[testThenApply]");
    CompletableFuture<String> cf = competableFutureFactory.createACompletableFuture(msgToTest);

    cf = cf.thenApply((msg) -> {
      log("Applying changes to .." + msg);
      return msg.toString().toUpperCase();
    });

    assertTrue(cf.getNow(null).equals(msgToTest.toUpperCase()));
    log("[testThenApply]");
  }

  @Test
  public void thenApplyAsync() {
    log("[thenApplyAsync]");

    final String ANOTHER_WORD = "ANOTHERWORD";
    String msg = "thenApplyAsync";
    CompletableFuture cf = competableFutureFactory.createACompletableFuture(msg);

    cf = cf.thenApplyAsync((m) -> {
      sleep(10000l);
      return ANOTHER_WORD;
    });

    assertNull(cf.getNow(null));

    assertEquals(ANOTHER_WORD, cf.join());

    log("[thenApplyAsync]");
  }

  @Test
  public void testConsumingResultPreviousStage() {
    StringBuilder result = new StringBuilder();
    CompletableFuture cf = competableFutureFactory.createACompletableFuture("message");
    cf = cf.thenAccept(s -> {
      result.append(s);
    });
    sleep(1000l);
    assertTrue("Result was empty", result.length() > 0);
  }

  @Test
  public void testConsumingAsyncResultPreviosStage() {
    StringBuilder result = new StringBuilder();

    CompletableFuture<String> cf = competableFutureFactory.createACompletableFuture(MESSAGE);

    cf.thenAcceptAsync(msg -> {
      result.append(msg);
    });

    assertTrue("Result was empty", result.toString().isEmpty());

  }

  @Test
  public void testApplyingFunctionResultAnotherFunction() {
    String method = "[testApplyingFunctionResultAnotherFunction]";
    log(method);
    CompletableFuture<String> upperCaseFunction =
        competableFutureFactory.transformToUpperCaseAsync(MESSAGE, ONE_SECOND);

    CompletableFuture<String> lowerCaseFunction =
        competableFutureFactory.transformToLowerCaseAsync(MESSAGE, ONE_SECOND);

    CompletableFuture<String> resultFunction =
        upperCaseFunction.applyToEither(lowerCaseFunction, (s) -> s + ANOTHER_MESSAGE);

    String result = resultFunction.join();

    log(result);

    assertTrue(result.endsWith(ANOTHER_MESSAGE));

    log(method);
  }


}

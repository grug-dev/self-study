package com.kkpa.java8.completablefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    private void log(String msg) {
	System.out.println(msg);
    }

    public CompletableFuture createACompletableFuture(String msg) {
	return CompletableFuture.completedFuture(msg);
    }

    public CompletableFuture runningSimpleAsyncStage() {
	CompletableFuture cf = CompletableFuture.runAsync(() -> {
	    sleep(TWO_SECONDS);
	    System.out.println("isDaemon: " + Thread.currentThread().isDaemon());
	    System.out.println("Finish SImple Async ");
	});

	return cf;
    }

    public CompletableFuture transformToUpperCaseAsync(String message, int timeDelayed) {

	CompletableFuture<String> cf = CompletableFuture.completedFuture(message).thenApplyAsync(String::toUpperCase);
	sleep(timeDelayed * ONE_THOUSAND);

	return cf;
    }

    public CompletableFuture transformToLowerCaseAsync(String message, int timeDelayed) {

	CompletableFuture<String> cf = CompletableFuture.completedFuture(message).thenApplyAsync(String::toLowerCase);
	sleep(timeDelayed * ONE_THOUSAND);

	return cf;
    }
    
    private int  calculateRating(Car car) {
	return new Random().nextInt(10);
    }

    private CompletableFuture<List> cars() {
	CompletableFuture<List> buildCars = CompletableFuture.supplyAsync(() -> {
		List cars = new ArrayList();

		cars.add(new Car("Ford", 4500));
		cars.add(new Car("Renaul", 2500));
		cars.add(new Car("Kia", 1500));
		cars.add(new Car("Mazda", 6500));
		
		sleep(ONE_THOUSAND);
		
	   return cars; 
	});
	return buildCars;
    }
}

package com.kkpa.multithreading.countdownlatches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

class Processor implements Runnable {

	private CountDownLatch latch;
	private int count = 0;

	public Processor(CountDownLatch latch, int i) {
		this.latch = latch;
		this.count = i;
	}

	public void run() {
		System.out.println("Started " + this.count + " - LatchCount: " + latch.getCount() + " Thread: "
				+ Thread.currentThread().getName());

		try {
			Thread.sleep(3000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		latch.countDown();
		System.out.println("Winds Up " + this.count + " - LatchCount: " + latch.getCount() + " Thread: "
				+ Thread.currentThread().getName());
	}
}

public class AppCountDownLatch {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);

		ExecutorService executor = Executors.newFixedThreadPool(4);

		IntStream.range(0, 8).forEach(i -> {
			executor.submit(new Processor(latch, i));
		});

		latch.await();

		System.out.println("Completed");
		executor.shutdown();

	}

}

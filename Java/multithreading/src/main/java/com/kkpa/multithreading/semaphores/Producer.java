package com.kkpa.multithreading.semaphores;

import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Producer implements Runnable {

	private int numberOfMessagesToProduce;
	private Queue<String> queue;
	private Semaphore mutex, queueSemaphore;

	@Override
	public void run() {
		IntStream.rangeClosed(1, numberOfMessagesToProduce).forEach((i) -> {
			try {

				mutex.acquire();
				String message = "Product " + i;

				queue.offer(message);
				String threadName = Thread.currentThread().getName();
				System.out.println(message + " was produced by: " + threadName);
				queueSemaphore.release(); // Free shared resource.
				mutex.release();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

}

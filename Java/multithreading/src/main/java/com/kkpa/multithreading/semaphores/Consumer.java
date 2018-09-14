package com.kkpa.multithreading.semaphores;

import java.util.Queue;
import java.util.concurrent.Semaphore;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Consumer implements Runnable {

	@NonNull
	private Queue<String> queue;
	@NonNull
	private Semaphore mutex, queueSemaphore;

	private volatile boolean stopConsuming;

	@Override
	public void run() {

		while (!stopConsuming) {
			try {
				queueSemaphore.acquire(); // Block resource shared because we are consuming it.
				mutex.acquire();

				String messageProduced = queue.poll();
				System.out.println("Thread : " + Thread.currentThread().getName() + " got " + messageProduced);
				Thread.sleep(500);
				mutex.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopConsumming() {
		this.stopConsuming = true;
	}

}
